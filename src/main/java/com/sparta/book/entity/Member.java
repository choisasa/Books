package com.sparta.book.entity;

import com.sparta.book.service.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "member1")
public class Member extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 이름
    @Column(nullable = false)
    private String name;

    // 성별
    @Column(nullable = false)
    private String gender;

    // 전화번호
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    // 주소
    @Column(nullable = false)
    private String address;

    // 주민번호
    @Column(nullable = false, unique = true)
    private String socialSecurityNumber;


    public Member(String name, String gender, String phoneNumber, String address, String socialSecurityNumber) {
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.socialSecurityNumber = socialSecurityNumber;
    }
}
