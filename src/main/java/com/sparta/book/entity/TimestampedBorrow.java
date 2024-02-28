package com.sparta.book.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TimestampedBorrow {

    // 대출 날짜
    @Column(updatable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime borrowDateAt;

    // 반납 날짜
    @Column
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime returnDateAt;
}
