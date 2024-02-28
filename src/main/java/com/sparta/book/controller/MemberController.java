package com.sparta.book.controller;

import com.sparta.book.dto.member.MemberRequestDto;
import com.sparta.book.dto.member.MemberResponseDto;
import com.sparta.book.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    // 회원 등록 API
    @PostMapping
    public ResponseEntity<?> registerMember(@RequestBody MemberRequestDto requestDto) {
        try {
            MemberResponseDto memberResponseDto = memberService.registerMember(requestDto);
            return ResponseEntity.ok().body(memberResponseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
