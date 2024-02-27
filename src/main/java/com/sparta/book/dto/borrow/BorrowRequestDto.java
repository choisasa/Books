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
@Data
public class BorrowRequestDto {

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

    public Borrow toEntity() {
        return Borrow.builder()
                .book(book)
                .member(member)
                .returnStatus(returnStatus)
                .borrowDateAt(borrowDateAt)
                .returnDateAt(returnDateAt)
                .build();
    }



}
