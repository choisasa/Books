package com.sparta.book.repository;

import com.sparta.book.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

        boolean existsByPhoneNumber(String phoneNumber);

        boolean existsBySocialSecurityNumber(String socialSecurityNumber);

}