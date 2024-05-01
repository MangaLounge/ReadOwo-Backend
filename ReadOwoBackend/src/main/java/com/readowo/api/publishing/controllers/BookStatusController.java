package com.readowo.api.publishing.controllers;



import com.readowo.api.publishing.Dtos.BookStatusDtos;
import com.readowo.api.publishing.Dtos.SaveBookStatusDtos;

import com.readowo.api.publishing.Models.BookStatus;

import com.readowo.api.publishing.Services.Communication.BookStatusResponse;
import com.readowo.api.publishing.Services.ServicesImpl.BookStatusServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/books-Status")
public class BookStatusController {


    private final BookStatusServiceImpl bookStatusService;
    private final ModelMapper modelMapper;

    @Autowired
    public BookStatusController(BookStatusServiceImpl bookStatusService, ModelMapper modelMapper) {
        this.bookStatusService = bookStatusService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<BookStatus>> getAllBooksStatus() {
        List<BookStatus> bookStatusList = bookStatusService.getAllBooksStatus();
        return ResponseEntity.ok().body(bookStatusList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookStatusById(@PathVariable Long id) {
        Optional<BookStatus> bookStatus = bookStatusService.getBookStatusById(id);
        return bookStatus.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<BookStatusDtos> saveBookStatus(@RequestBody SaveBookStatusDtos saveBookStatusDtos) {
        BookStatus bookStatus = modelMapper.map(saveBookStatusDtos, BookStatus.class);
        BookStatus createdBookStatus = bookStatusService.saveBookStatus(saveBookStatusDtos);
        BookStatusDtos bookStatusDtos = modelMapper.map(createdBookStatus, BookStatusDtos.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookStatusDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookStatusResponse> updateBookStatus(@PathVariable("id") Long id, @RequestBody SaveBookStatusDtos saveBookStatusDtos) {
        BookStatusResponse response = bookStatusService.updateBookStatus(id, saveBookStatusDtos);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookStatusResponse> deleteBookStatus(@PathVariable("id") Long bookStatusId) {
        BookStatusResponse response = bookStatusService.deleteBookStatus(bookStatusId);
        return ResponseEntity.ok().body(response);
    }
}
