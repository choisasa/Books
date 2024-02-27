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
@Table(name = "borrow")
public class Borrow extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    // 책 아이디
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    // 회원 아이디
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // 반납 여부
    @Column(nullable = false)
    private boolean returnStatus;

    // 대출 날짜 / 반납 날짜
    @Column(nullable = false)
    private LocalDateTime borrowDateAt;
    private LocalDateTime returnDateAt;

    public Borrow(Book book, Member member, boolean returnStatus, LocalDateTime borrowDateAt, LocalDateTime returnDateAt) {
        this.book = book;
        this.member = member;
        this.returnStatus = returnStatus;
        this.borrowDateAt = borrowDateAt;
        this.returnDateAt = returnDateAt;
    }
}
