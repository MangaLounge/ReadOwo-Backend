package com.readowo.api.publishing.Services.IServices;

import com.readowo.api.publishing.Dtos.SaveGenreDtos;
import com.readowo.api.publishing.Models.Genre;
import com.readowo.api.publishing.Services.Communication.GenreResponse;

import java.util.List;
import java.util.Optional;

public interface IGenreService {

    List<Genre> listGenres();
    Optional<Genre> findGenreById(Long genreId);
    Genre saveGenre(SaveGenreDtos saveGenreDtos);
    GenreResponse updateGenre(Long id, SaveGenreDtos saveGenreDtos);
    GenreResponse deleteGenre(Long genreId);
}
