package com.sparta.book.repository;

import com.sparta.book.dto.borrow.GetBorrowResponseDto;
import com.sparta.book.entity.Book;
import com.sparta.book.entity.Borrow;
import com.sparta.book.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    Borrow findByBook(Book book);

    // count 있으면 true, 없으면 false
    boolean existsByBook(Book book);

    boolean existsByMember(Member member);

    List<Borrow> findAllByMemberOrderByBorrowDateAtAsc(Member member);
}
