package com.sparta.book.dto;

import com.sparta.book.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@NoArgsConstructor
public class MemberRequestDto {
    private String name;
    private String gender;
    private String phoneNumber;
    private String address;
    private String socialSecurityNumber;

    @Builder
    public MemberRequestDto(String name, String gender, String phoneNumber, String address, String socialSecurityNumber) {
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .gender(gender)
                .address(address)
                .phoneNumber(phoneNumber)
                .socialSecurityNumber(socialSecurityNumber)
                .build();
    }
}
