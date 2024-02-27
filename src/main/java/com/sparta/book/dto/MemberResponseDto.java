package com.sparta.book.dto;

import com.sparta.book.entity.Member;
import lombok.Builder;


public class MemberResponseDto {
    private Long id;
    private String name;
    private String gender;
    private String phoneNumber;
    private String address;
    private String socialSecurityNumber;

    @Builder
    public MemberResponseDto(String name, String gender, String phoneNumber, String address, String socialSecurityNumber){
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.socialSecurityNumber = socialSecurityNumber;
    }

//    public MemberResponseDto(Member member) {
//        this.name = member.getName();
//        this.gender = member.getGender();
//        this.phoneNumber = member.getPhoneNumber();
//        this.address = member.getAddress();
//        this.socialSecurityNumber = member.getSocialSecurityNumber();
//    }

}
