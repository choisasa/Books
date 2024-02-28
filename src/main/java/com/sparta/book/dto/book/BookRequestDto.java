package com.sparta.book.dto.book;

import com.sparta.book.entity.Book;
import com.sparta.book.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BookRequestDto {
    private String title;
    private String author;
    private String language;
    private String publisher;

    @Builder
    public BookRequestDto(String title, String author, String language, String publisher) {
        this.title = title;
        this.author = author;
        this.language = language;
        this.publisher = publisher;
    }

    public Book toEntity() {
        return Book.builder()
                .title(title)
                .author(author)
                .language(language)
                .publisher(publisher)
                .build();
    }
}