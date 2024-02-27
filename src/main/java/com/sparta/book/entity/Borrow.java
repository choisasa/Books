package com.sparta.book.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.book.dto.borrow.BorrowResponseDto;
import com.sparta.book.dto.member.MemberResponseDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "borrow")
public class Borrow {
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

    // 대출 날짜
    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime borrowDateAt;

    // 반납 날짜
    @Column
    @LastModifiedDate
    private LocalDateTime returnDateAt;

    @Builder
    public Borrow(Book book, Member member, boolean returnStatus, LocalDateTime borrowDateAt, LocalDateTime returnDateAt) {
        this.book = book;
        this.member = member;
        this.returnStatus = returnStatus;
        this.borrowDateAt = borrowDateAt;
        this.returnDateAt = returnDateAt;
    }

    public void returned(LocalDateTime returnedAt) {
        this.returnStatus = true;
        this.returnDateAt = returnedAt;
    }

    public BorrowResponseDto of() {
        return BorrowResponseDto.builder()
                .book(book)
                .member(member)
                .returnStatus(returnStatus)
                .borrowDateAt(borrowDateAt)
                .returnDateAt(returnDateAt)
                .build();
    }
}

