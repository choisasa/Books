package com.sparta.book.entity;

import com.sparta.book.dto.borrow.BorrowResponseDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "borrow")
public class Borrow extends TimestampedBorrow{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 책 아이디
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    // 회원 아이디
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // 반납 여부
    @Column(nullable = false)
    private boolean returnStatus;

    @Builder
    public Borrow(Book book, Member member, boolean returnStatus, LocalDateTime borrowDateAt, LocalDateTime returnDateAt) {
        this.book = book;
        this.member = member;
        this.returnStatus = returnStatus;
    }

    public BorrowResponseDto of() {
        return BorrowResponseDto.builder()
                .book(book.getId())
                .member(member.getId())
                .returnStatus(returnStatus)
                .build();
    }
}

