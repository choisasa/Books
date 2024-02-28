package com.sparta.book.dto.borrow;

import com.sparta.book.entity.Borrow;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BorrowResponseDto {

    // PK
    private Long id;

    // 책 아이디
    private Long bookId;

    // 회원 아이디
    private Long memberId;

    // 반납 여부
    private boolean returnStatus;

    // 대출 날짜
    private LocalDateTime borrowDateAt;

    // 반납 날짜
    private LocalDateTime returnDateAt;

    @Builder
    public BorrowResponseDto(Long id, Long book, Long member, boolean returnStatus, LocalDateTime borrowDateAt, LocalDateTime returnDateAt){
        this.id = id;
        this.bookId = book;
        this.memberId = member;
        this.returnStatus = returnStatus;
        this.borrowDateAt = borrowDateAt;
        this.returnDateAt = returnDateAt;
    }

    public BorrowResponseDto(Borrow borrow){
        this.id = borrow.getId();
        this.bookId = borrow.getBook().getId();
        this.memberId = borrow.getMember().getId();
        this.returnStatus = borrow.isReturnStatus();
        this.borrowDateAt = borrow.getBorrowDateAt();
        this.returnDateAt = borrow.getReturnDateAt();
    }

}

