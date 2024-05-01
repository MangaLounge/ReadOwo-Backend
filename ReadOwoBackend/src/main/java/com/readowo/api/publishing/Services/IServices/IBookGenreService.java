package com.readowo.api.publishing.Services.IServices;


import com.readowo.api.publishing.Dtos.SaveBookGenreDtos;
import com.readowo.api.publishing.Dtos.SaveGenreDtos;
import com.readowo.api.publishing.Models.BookGenre;
import com.readowo.api.publishing.Services.Communication.BookGenreResponse;

import java.util.List;
import java.util.Optional;

public interface IBookGenreService {

    List<BookGenre> list();
    Optional<BookGenre> findById(Long id);

    BookGenre saveBookGenre(SaveGenreDtos saveBookGenreDtos);
    BookGenreResponse updateBookGenre(Long id, SaveBookGenreDtos saveBookGenreDtos);
    BookGenreResponse delete(Long bookGenreId);
}
