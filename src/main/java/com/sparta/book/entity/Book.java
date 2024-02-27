package com.sparta.book.entity;

import com.sparta.book.dto.book.BookResponseDto;
import com.sparta.book.dto.member.MemberResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name ="book")
public class Book extends TimestampedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 책 제목
    @Column(nullable = false)
    private String title;
    // 저자
    @Column(nullable = false)
    private String author;
    // 언어
    @Column(nullable = false)
    private String language;
    // 출판사
    @Column(nullable = false)
    private String publisher;
    // 등록일
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public Book(String title, String author, String language, String publisher, LocalDateTime createdAt) {
        this.title = title;
        this.author = author;
        this.language = language;
        this.publisher = publisher;
        this.createdAt = createdAt;
    }

    public BookResponseDto of() {
        return BookResponseDto.builder()
                .title(title)
                .author(author)
                .language(language)
                .publisher(publisher)
                .createdAt(createdAt)
                .build();
    }
}
