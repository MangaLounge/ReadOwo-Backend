package com.readowo.api.publishing.controllers;


import com.readowo.api.publishing.Dtos.*;
import com.readowo.api.publishing.Models.BookGenre;
import com.readowo.api.publishing.Models.BookStatus;
import com.readowo.api.publishing.Services.Communication.BookGenreResponse;
import com.readowo.api.publishing.Services.Communication.BookStatusResponse;
import com.readowo.api.publishing.Services.ServicesImpl.BookGenreServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/book-Genres")
public class BookGenreController {

    private final ModelMapper modelMapper;
    private final BookGenreServiceImpl bookGenreService;

    public BookGenreController(BookGenreServiceImpl bookGenreService , ModelMapper modelMapper) {
        this.bookGenreService = bookGenreService;
        this.modelMapper=modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<BookGenre>> listBookGenres() {
        List<BookGenre> bookGenres = bookGenreService.list();
        return new ResponseEntity<>(bookGenres, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookGenre> findBookGenreById(@PathVariable Long id) {
        Optional<BookGenre> bookGenre = bookGenreService.findById(id);
        return bookGenre.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<BookGenreDtos> saveBookGenre(@RequestBody SaveGenreDtos saveGenreDtos) {
        BookGenre bookGenre = modelMapper.map(saveGenreDtos,BookGenre.class);
        BookGenre createBookGenre = bookGenreService.saveBookGenre(saveGenreDtos);
        BookGenreDtos bookGenreDtos= modelMapper.map(createBookGenre, BookGenreDtos.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookGenreDtos);

    }

    @PutMapping("/{id}")
    public ResponseEntity<BookGenreResponse> updateBookGenre(@PathVariable("id") Long id, @RequestBody SaveBookGenreDtos saveBookGenreDtos) {
        BookGenreResponse response = bookGenreService.updateBookGenre(id, saveBookGenreDtos);
        return ResponseEntity.ok().body(response);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<BookGenreResponse> deleteBookGenre(@PathVariable Long id) {
        BookGenreResponse response = bookGenreService.delete(id);
        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}


