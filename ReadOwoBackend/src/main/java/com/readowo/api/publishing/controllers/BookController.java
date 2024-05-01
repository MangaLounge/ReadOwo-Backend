package com.readowo.api.publishing.controllers;

import com.readowo.api.publishing.Dtos.BookDtos;
import com.readowo.api.publishing.Dtos.ChapterDtos;
import com.readowo.api.publishing.Dtos.SaveBookDtos;
import com.readowo.api.publishing.Dtos.SaveChapterDtos;
import com.readowo.api.publishing.Models.Book;
import com.readowo.api.publishing.Models.Chapters;
import com.readowo.api.publishing.Services.Communication.BookResponse;
import com.readowo.api.publishing.Services.ServicesImpl.BookServiceImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/books")

public class BookController {
    private final BookServiceImpl bookService;

    private final ModelMapper modelMapper;

    @Autowired
    public BookController(BookServiceImpl bookService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<BookDtos>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<BookDtos> bookDtos = books.stream()
                .map(book -> modelMapper.map(book, BookDtos.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDtos> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        if (book != null) {
            BookDtos bookDtos = modelMapper.map(book, BookDtos.class);
            return ResponseEntity.ok(bookDtos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<BookDtos> createBook(@RequestBody SaveBookDtos saveBookDtos) {
        Book book = modelMapper.map(saveBookDtos, Book.class);
        Book createdBook = bookService.saveBook(saveBookDtos);
        BookDtos bookDtos = modelMapper.map(createdBook, BookDtos.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookDtos);
    }



    @PutMapping("/{id}")
    public ResponseEntity<BookDtos> updateBook(@PathVariable Long id, @Valid @RequestBody SaveBookDtos saveBookDtos) {

        BookResponse response = bookService.updateBook(id,saveBookDtos);
        if (response.isSuccess()) {
            BookDtos bookDtos = modelMapper.map(response.getResource(), BookDtos.class);
            return ResponseEntity.ok(bookDtos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
