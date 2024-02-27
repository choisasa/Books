package com.sparta.book.dto.member;

import lombok.Builder;
import lombok.Getter;


@Getter
public class MemberResponseDto {
    private Long id;
    private String name;
    private String gender;
    private String phoneNumber;
    private String address;

    @Builder
    public MemberResponseDto(Long id, String name, String gender, String phoneNumber, String address){
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

}
