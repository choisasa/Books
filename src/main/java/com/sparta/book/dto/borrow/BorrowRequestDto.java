package com.sparta.book.dto.borrow;


import com.sparta.book.entity.Book;
import com.sparta.book.entity.Borrow;
import com.sparta.book.entity.Member;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BorrowRequestDto {

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

    public Borrow toEntity(Book book, Member member) {
        return Borrow.builder()
                .book(book)
                .member(member)
                .build();
    }



}
