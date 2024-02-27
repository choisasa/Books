package com.sparta.book.repository;

import com.sparta.book.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    Borrow findByBook_Id(Long bookId);

    List<Borrow> findByMember_IdOrderByBorrowDateAtDesc(Long memberId);

}
