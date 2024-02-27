package com.sparta.book.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberRequestDto {
    private String name;
    private String gender;
    private String phoneNumber;
    private String address;
    private String socialSecurityNumber;

    public MemberRequestDto(String name, String gender, String phoneNumber, String address, String socialSecurityNumber) {
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.socialSecurityNumber = socialSecurityNumber;
    }
}
