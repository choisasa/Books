package com.sparta.book.service;

import com.sparta.book.dto.book.BookResponseDto;
import com.sparta.book.dto.borrow.BorrowRequestDto;
import com.sparta.book.dto.borrow.BorrowResponseDto;
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
    public BorrowResponseDto borrowBook(BorrowRequestDto borrowRequestDto) {

        Book book = bookRepository.findById(borrowRequestDto.getBookId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 도서입니다."));

        log.info(String.valueOf(book.getId()));

        Member member = memberRepository.findById(borrowRequestDto.getMemberId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (isBookAvailableForBorrow(book)) {
            throw new IllegalArgumentException("반납하지 않은 책이 있습니다.");
        }

        Borrow borrow = borrowRepository.save(borrowRequestDto.toEntity(book, member));

        return borrow.of();
    }

    // 반납
    @Transactional
    public void returnBook(Long bookId) {

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 도서입니다."));

        if (!isBookAvailableForBorrow(book)) {
            throw new IllegalArgumentException("이미 반납된 책입니다.");
        }

        Borrow borrow = borrowRepository.findByBook(book);

    }

    // 회원 대출 목록 조회
    @Transactional
    public List<GetBorrowResponseDto> getBorrowList(Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        return borrowRepository.findByMemberOrderByBorrowDateAtDesc(member).stream().map(GetBorrowResponseDto::new).toList();
    }

    private boolean isBookAvailableForBorrow(Book book) {

        // 도서 식별값으로 대출 내역 조회
        Borrow borrow = borrowRepository.findByBook(book);

        // 대출 내역이 없거나 모든 대출이 반납된 경우에만 대출 가능
        return borrow.isReturnStatus();
    }
}
