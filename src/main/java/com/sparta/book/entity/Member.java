package com.sparta.book.entity;

import com.sparta.book.dto.member.MemberResponseDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member {
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


    @Builder
    public Member(String name, String gender, String phoneNumber, String address, String socialSecurityNumber) {
       this.name = name;
       this.gender = gender;
       this.phoneNumber = phoneNumber;
       this.address = address;
       this.socialSecurityNumber = socialSecurityNumber;
    }

    public MemberResponseDto of() {
        return MemberResponseDto.builder()
                .name(name)
                .gender(gender)
                .phoneNumber(phoneNumber)
                .address(address)
                .build();
    }
}
