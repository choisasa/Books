package com.sparta.book.service;

import com.sparta.book.dto.borrow.BorrowRequestDto;
import com.sparta.book.dto.borrow.GetBorrowResponseDto;
import com.sparta.book.entity.Borrow;
import com.sparta.book.repository.BookRepository;
import com.sparta.book.repository.BorrowRepository;
import com.sparta.book.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    // 도서대출
    public void borrowBook(BorrowRequestDto borrowRequestDto) {

        Long bookId = borrowRequestDto.getBook().getId(); // request 에서 도서 식별값 추출
        Long memberId = borrowRequestDto.getMember().getId(); // 회원 식별값 추출

        if (isBookAvailableForBorrow(bookId)) {
            throw new IllegalArgumentException("반납하지 않은 책이 있습니다.");
        }

        borrowRepository.save(borrowRequestDto.toEntity());

    }

    // 반납
    @Transactional
    public void returnBook(Long bookId) {

        if(!isBookAvailableForBorrow(bookId)) {
            throw new IllegalArgumentException("이미 반납된 책입니다.");
        }

        Borrow borrow = borrowRepository.findByBook_Id(bookId);

        borrow.returned(LocalDateTime.now());
    }

    // 회원 대출 목록 조회
    @Transactional
    public List<GetBorrowResponseDto> getBorrowList(Long memberId) {

        return borrowRepository.findByMember_IdOrderByBorrowDateAtDesc(memberId).stream().map(GetBorrowResponseDto::new).toList();
    }

    private boolean isBookAvailableForBorrow(Long book) {
        // 도서 식별값으로 대출 내역 조회
        Borrow borrow = borrowRepository.findByBook_Id(book);

        // 대출 내역이 없거나 모든 대출이 반납된 경우에만 대출 가능
        return borrow.isReturnStatus();
    }
}
