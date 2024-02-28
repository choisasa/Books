package com.sparta.book.dto.book;

import com.sparta.book.entity.Book;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookResponseDto {

    private String title;
    private String author;
    private String language;
    private String publisher;
    private LocalDateTime createdAt;

    @Builder
    public BookResponseDto(String title, String author, String language, String publisher, LocalDateTime createdAt, LocalDateTime modifiedAt){
        this.title = title;
        this.author = author;
        this.language = language;
        this.publisher = publisher;
        this.createdAt = createdAt;
    }

    public BookResponseDto(Book book){
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.language = book.getLanguage();
        this.publisher = book.getPublisher();
        this.createdAt = book.getCreatedAt();
    }

}
