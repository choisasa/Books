package com.sparta.book.controller;

import com.sparta.book.dto.borrow.BorrowRequestDto;
import com.sparta.book.dto.borrow.GetBorrowResponseDto;
import com.sparta.book.service.BorrowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/borrow")
@Slf4j
public class BorrowController {

    private final BorrowService borrowService;

    // 대출
    @PostMapping
    public ResponseEntity<?> borrowBook(@RequestBody BorrowRequestDto borrowRequestDto) {
        try{
            borrowService.borrowBook(borrowRequestDto);
            return ResponseEntity.ok("도서 대출에 성공했습니다.");
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 반납
    @PutMapping("/{bookId}")
    public ResponseEntity<?> returnBook(@PathVariable Long bookId){
        try{
            borrowService.returnBook(bookId);
            return ResponseEntity.ok("도서 반납에 성공했습니다.");
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 조회
    @GetMapping("/{memberId}")
    public ResponseEntity<?> getBorrowList(@PathVariable Long memberId) {
        try{
            List<GetBorrowResponseDto> getBorrowList = borrowService.getBorrowList(memberId);
            return ResponseEntity.ok().body(getBorrowList);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
