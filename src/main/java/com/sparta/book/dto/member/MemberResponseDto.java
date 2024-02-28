package com.sparta.book.dto.member;

import lombok.Builder;
import lombok.Getter;


@Getter
public class MemberResponseDto {
    private String name;
    private String gender;
    private String phoneNumber;
    private String address;

    @Builder
    public MemberResponseDto(String name, String gender, String phoneNumber, String address){
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

}
