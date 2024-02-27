package com.sparta.book.service;

import com.sparta.book.dto.BookRequestDto;
import com.sparta.book.dto.BookResponseDto;
import com.sparta.book.entity.Book;
import com.sparta.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    // 도서 등록 기능
    public BookResponseDto registerBook(BookRequestDto requestDto){
        Book book = new Book(requestDto.getTitle(), requestDto.getAuthor(),requestDto.getLanguage(),
                requestDto.getPublisher(), requestDto.getCreateAt());
        Book saveBook = bookRepository.save(book);
        return new BookResponseDto(saveBook);
    }

    // 선택한 도서 정보 조회 기능
    public BookResponseDto getBook(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(" 아이디에 해당하는 책이 없습니다. " + id));
        return new BookResponseDto(book);
    }

    // 도서 목록 조회
    public List<BookResponseDto> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(BookResponseDto::new)
                .collect(Collectors.toList());
    }
}
