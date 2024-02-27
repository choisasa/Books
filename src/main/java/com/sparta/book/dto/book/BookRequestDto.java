package com.sparta.book.dto.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BookRequestDto {
    private String title;
    private String author;
    private String language;
    private String publisher;
    private LocalDateTime createAt;

    public BookRequestDto(String title, String author, String language, String publisher, LocalDateTime createAt) {
        this.title = title;
        this.author = author;
        this.language = language;
        this.publisher = publisher;
        this.createAt = createAt;
    }
}