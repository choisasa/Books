package com.sparta.book.controller;

import com.sparta.book.dto.borrow.BorrowRequestDto;
import com.sparta.book.dto.borrow.BorrowResponseDto;
import com.sparta.book.dto.borrow.GetBorrowResponseDto;
import com.sparta.book.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/borrow")
public class BorrowController {

    private final BorrowService borrowService;

    // 대출
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> borrowBook(@RequestBody BorrowRequestDto borrowRequestDto) {
        BorrowResponseDto borrowResponseDto = borrowService.borrowBook(borrowRequestDto);

        return ResponseEntity.ok(borrowResponseDto);
    }

    // 반납
    @PutMapping("/{bookId}")
    public void returnBook(@PathVariable Long bookId){
        borrowService.returnBook(bookId);
    }

    // 조회
    @GetMapping("/{memberId}")
    public List<GetBorrowResponseDto> getBorrowList(@PathVariable Long memberId) {
        return borrowService.getBorrowList(memberId);
    }

}
