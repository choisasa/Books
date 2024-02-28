package com.sparta.book.controller;

import com.sparta.book.dto.member.MemberRequestDto;
import com.sparta.book.dto.member.MemberResponseDto;
import com.sparta.book.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class MemberController {
    private final MemberService memberService;

    // 회원 등록 API
    @PostMapping
    public MemberResponseDto registerUser(@RequestBody MemberRequestDto requestDto) {
        return memberService.registerUser(requestDto);
    }

}
