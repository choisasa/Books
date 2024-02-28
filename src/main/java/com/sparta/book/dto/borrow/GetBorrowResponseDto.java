package com.sparta.book.dto.borrow;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.book.entity.Borrow;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetBorrowResponseDto {

    private Long id;
    private String title;
    private String author;
    private String name;
    private String phoneNumber;
    private boolean returnStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime borrowDateAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime returnDateAt;

    public GetBorrowResponseDto(Borrow borrow) {
        this.id = borrow.getId();
        this.title = borrow.getBook().getTitle();
        this.author = borrow.getBook().getAuthor();
        this.name = borrow.getMember().getName();
        this.phoneNumber = borrow.getMember().getPhoneNumber();
        this.returnStatus = borrow.isReturnStatus();
        this.borrowDateAt = borrow.getBorrowDateAt();
        this.returnDateAt = borrow.getReturnDateAt();
    }
}
