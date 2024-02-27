package com.sparta.book.controller;

import com.sparta.book.dto.book.BookRequestDto;
import com.sparta.book.dto.book.BookResponseDto;
import com.sparta.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    // 도서 등록 API
    @PostMapping
    public BookResponseDto registerBook(@RequestBody BookRequestDto requestDto){
        return bookService.registerBook(requestDto);
        // 요청에 대한 성공, 실패 여부 추가??
    }

    // 선택 도서 정보 조회 API
    @GetMapping("/{id}")
    public BookResponseDto getBook(@PathVariable Long id){
        return bookService.getBook(id);
    }

    // 도서 목록 조회 API
    @GetMapping
    public List<BookResponseDto> getAllBooks(){
        return bookService.getAllBooks();
    }
}

