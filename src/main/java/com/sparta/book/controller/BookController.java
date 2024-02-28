package com.sparta.book.controller;

import com.sparta.book.dto.book.BookRequestDto;
import com.sparta.book.dto.book.BookResponseDto;
import com.sparta.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    // 도서 등록 API
    @PostMapping
    public ResponseEntity<?> registerBook(@RequestBody BookRequestDto requestDto) {
        try {
            BookResponseDto bookResponseDto = bookService.registerBook(requestDto);
            return ResponseEntity.ok().body(bookResponseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 선택 도서 정보 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id) {
        try {
            BookResponseDto bookResponseDto = bookService.getBook(id);
            return ResponseEntity.ok().body(bookResponseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    // 도서 목록 조회 API
    @GetMapping
    public List<BookResponseDto> getAllBooks() {
        return bookService.getAllBooks();
    }
}

