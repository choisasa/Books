package com.sparta.book.entity;

import com.sparta.book.service.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="book")
public class Book extends Timestamped {

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
    private LocalDateTime createAt;

    public Book(String title, String author, String language, String publisher, LocalDateTime createdAt) {
        this.title = title;
        this.author = author;
        this.language = language;
        this.publisher = publisher;
        this.createAt = createdAt;
    }
}
