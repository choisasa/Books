package com.sparta.book.service;

import com.sparta.book.dto.member.MemberRequestDto;
import com.sparta.book.dto.member.MemberResponseDto;
import com.sparta.book.entity.Member;
import com.sparta.book.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponseDto registerMember(MemberRequestDto requestDto) {

        // 전화번호, 주민번호 중복 확인해주기
        boolean equalPhoneNumber = memberRepository.existsByPhoneNumber(requestDto.getPhoneNumber());
        boolean equalSocialSecurityNumber = memberRepository.existsBySocialSecurityNumber(requestDto.getSocialSecurityNumber());

        // 중복되는 경우 예외처리
        if (equalPhoneNumber){
            throw new IllegalArgumentException("이미 등록된 전화번호입니다.");
        }
        if(equalSocialSecurityNumber){
            throw new IllegalArgumentException("이미 등록된 주민등록번호입니다.");
        }

        // 회원 등록
        Member member =  memberRepository.save(requestDto.toEntity());
        return member.of();
    }

}
