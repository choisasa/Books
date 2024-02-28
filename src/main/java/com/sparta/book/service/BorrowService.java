package com.sparta.book.service;

import com.sparta.book.dto.borrow.BorrowRequestDto;
import com.sparta.book.dto.borrow.GetBorrowResponseDto;
import com.sparta.book.entity.Book;
import com.sparta.book.entity.Borrow;
import com.sparta.book.entity.Member;
import com.sparta.book.repository.BookRepository;
import com.sparta.book.repository.BorrowRepository;
import com.sparta.book.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    // 대출
    public void borrowBook(BorrowRequestDto borrowRequestDto) {

        // 도서 조회
        Book book = bookRepository.findById(borrowRequestDto.getBookId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 도서입니다."));

        // 멤버 조회
        Member member = memberRepository.findById(borrowRequestDto.getMemberId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        // Dto -> Entity
        Borrow borrow = borrowRequestDto.toEntity(book, member);

        // 대출 기록이 있는지 조회 -> 없으면 if문 실행
        if (!borrowRepository.existsByBook(book)) {

            // 대출 상태 변경
            borrow.returnConvert();

            // 대출 기록 저장
            borrowRepository.save(borrow);
        }

        // 대출 가능 도서인지 조회(false -> 대출됨, true -> 대출 가능)
        if (!isBookAvailableForBorrow(book)) {
            throw new IllegalArgumentException("이미 대출된 도서입니다.");
        }

        // 반납 상태 변경 (true -> false)
        borrow.returnConvert();

        // 대출 기록 저장
        borrowRepository.save(borrow);
    }

    // 반납
    @Transactional
    public void returnBook(Long bookId) {

        // book_id 로 book 객체 가져오기
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 도서입니다."));

        // 가져온 객체로 데이터 조회
        Borrow borrow = borrowRepository.findByBook(book);

        // 대출 기록 조회
        if (!borrowRepository.existsByBook(book)) {
            throw new IllegalArgumentException("대출 기록이 없습니다.");
        }

        // 반납된 도서인지 조회 (true -> 반납된 도서)
        if (isBookAvailableForBorrow(book)) {
            throw new IllegalArgumentException("이미 반납된 책입니다.");
        }

        // 반납 상태 변경 (false -> true)
        borrow.returnConvert();

    }

    // 회원 대출 목록 조회
    @Transactional
    public List<GetBorrowResponseDto> getBorrowList(Long memberId) {

        // 회원 식별값으로 member 테이블 조회
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        // 대출 기록 조회
        if (!borrowRepository.existsByMember(member)) {
            throw new IllegalArgumentException("대출 기록이 없습니다.");
        }

        // 회원 식별값으로 borrow 테이블 조회후 리스트로 만들어서 반환
        return borrowRepository.findAllByMemberOrderByBorrowDateAtAsc(member).stream().map(GetBorrowResponseDto::new).toList();
    }

    // 대출 가능 도서인지 조회해주는 method
    private boolean isBookAvailableForBorrow(Book book) {

        // 도서 식별값으로 대출 내역 조회
        Borrow borrow = borrowRepository.findByBook(book);

        // 대출 내역 반환
        return borrow.isReturnStatus();
    }
}
