package com.sparta.book.repository;

import com.sparta.book.entity.Book;
import com.sparta.book.entity.Borrow;
import com.sparta.book.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    Borrow findByBook(Book book);

    List<Borrow> findByMemberOrderByBorrowDateAtDesc(Member member);

}
