package com.readowo.api.publishing.controllers;


import com.readowo.api.publishing.Dtos.ChapterDtos;
import com.readowo.api.publishing.Dtos.GenreDtos;
import com.readowo.api.publishing.Dtos.SaveChapterDtos;
import com.readowo.api.publishing.Dtos.SaveGenreDtos;
import com.readowo.api.publishing.Models.Chapters;
import com.readowo.api.publishing.Models.Genre;
import com.readowo.api.publishing.Services.Communication.ChapterResponse;
import com.readowo.api.publishing.Services.Communication.GenreResponse;
import com.readowo.api.publishing.Services.ServicesImpl.GenreServiceImpl;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/genres")
@AllArgsConstructor
public class GenreController {
    private final GenreServiceImpl genreService;
    private final ModelMapper modelMapper;


    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> genres = genreService.listGenres();
        return ResponseEntity.ok().body(genres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable("id") Long genreId) {
        Optional<Genre> genre = genreService.findGenreById(genreId);
        return genre.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GenreDtos> saveGenre(@RequestBody SaveGenreDtos saveGenreDtos) {
        Genre genre = modelMapper.map(saveGenreDtos, Genre.class);
        Genre createdGenre = genreService.saveGenre(saveGenreDtos);
        GenreDtos genreDtos = modelMapper.map(createdGenre, GenreDtos.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(genreDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreResponse> updateGenre(@PathVariable("id") Long id, @RequestBody SaveGenreDtos saveGenreDtos) {
        GenreResponse response = genreService.updateGenre(id, saveGenreDtos);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenreResponse> deleteGenre(@PathVariable("id") Long genreId) {
        GenreResponse response = genreService.deleteGenre(genreId);
        return ResponseEntity.ok().body(response);
    }
}
