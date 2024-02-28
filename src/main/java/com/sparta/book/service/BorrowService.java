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
@Slf4j
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    // 도서대출
    public void borrowBook(BorrowRequestDto borrowRequestDto) {

        // 도서 조회
        Book book = bookRepository.findById(borrowRequestDto.getBookId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 도서입니다."));
        log.info("book.getId : {}", book.getId());

        Member member = memberRepository.findById(borrowRequestDto.getMemberId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));


        Borrow borrow = borrowRequestDto.toEntity(book, member);

        // borrow에 데이터가 있는지 조회 -> 없으면 if문 실행
        if(!borrowRepository.existsByBook(book)) {
            // 대출 상태 변경
            borrow.returnConvert();

            // 대출 기록 저장
            borrowRepository.save(borrow);
        }

        // 대출 도서인지 조회(false -> 대출가능, true -> 대출된 도서)
        if (!isBookAvailableForBorrow(book)) {
            throw new IllegalArgumentException("이미 대출된 도서입니다.");
        }

        // 대출 상태 변경
        borrow.returnConvert();

        // 대출 기록 저장
        borrowRepository.save(borrow);
    }

    // 반납
    @Transactional
    public void returnBook(Long bookId) {
        log.info("반납 요청된 도서 ID : {}", bookId);

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 도서입니다."));

        log.info("조회된 도서 : {}", book.getId());
        Borrow borrow = borrowRepository.findByBook(book);

        // 대출 기록 조회
        if(!borrowRepository.existsByBook(book)){
            throw new IllegalArgumentException("대출 기록이 없습니다.");
        }

        if (isBookAvailableForBorrow(book)) {
            throw new IllegalArgumentException("이미 반납된 책입니다.");
        }
            borrow.returnConvert();

    }

    // 회원 대출 목록 조회
    @Transactional
    public List<GetBorrowResponseDto> getBorrowList(Long memberId) {

        // 회원 식별값으로 member 테이블 조회
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        // 회원 식별값으로 borrow 테이블에서
        List<GetBorrowResponseDto> borrowList = borrowRepository.findByMemberOrderByBorrowDateAtAsc(member).stream().map(GetBorrowResponseDto::new).toList();

        if (borrowList.isEmpty()) {
            throw new IllegalArgumentException("대출 기록이 없습니다.");
        }

        return borrowList;
    }

    // 대출 확인
    private boolean isBookAvailableForBorrow(Book book) {

        // 도서 식별값으로 대출 내역 조회
        Borrow borrow = borrowRepository.findByBook(book);

        // 대출 내역이 없거나 모든 대출이 반납된 경우에만 대출 가능
        return borrow.isReturnStatus();
    }
}
