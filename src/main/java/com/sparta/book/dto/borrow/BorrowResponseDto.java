package com.sparta.book.dto.borrow;

import com.sparta.book.entity.Book;
import com.sparta.book.entity.Member;
import lombok.Builder;

import java.time.LocalDateTime;

public class BorrowResponseDto {

    // PK
    private Long id;

    // 책 아이디
    private Book book;

    // 회원 아이디
    private Member member;

    // 반납 여부
    private boolean returnStatus;

    // 대출 날짜
    private LocalDateTime borrowDateAt;

    // 반납 날짜
    private LocalDateTime returnDateAt;

    @Builder
    public BorrowResponseDto(Book book, Member member, boolean returnStatus, LocalDateTime borrowDateAt, LocalDateTime returnDateAt){
        this.book = book;
        this.member = member;
        this.returnStatus = returnStatus;
        this.borrowDateAt = borrowDateAt;
        this.returnDateAt = returnDateAt;
    }


}

