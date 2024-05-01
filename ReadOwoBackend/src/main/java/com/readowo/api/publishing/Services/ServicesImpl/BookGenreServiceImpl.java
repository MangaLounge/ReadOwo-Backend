package com.readowo.api.publishing.Services.ServicesImpl;


import com.readowo.api.Shared.Persistence.Repositories.UnitOfWork;
import com.readowo.api.publishing.Dtos.*;
import com.readowo.api.publishing.Models.Book;
import com.readowo.api.publishing.Models.BookGenre;
import com.readowo.api.publishing.Models.BookStatus;
import com.readowo.api.publishing.Models.Genre;
import com.readowo.api.publishing.Repositories.BookGenreRepository;
import com.readowo.api.publishing.Repositories.BookRepository;
import com.readowo.api.publishing.Repositories.GenreRepository;
import com.readowo.api.publishing.Services.Communication.BookGenreResponse;
import com.readowo.api.publishing.Services.Communication.BookStatusResponse;
import com.readowo.api.publishing.Services.IServices.IBookGenreService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookGenreServiceImpl implements IBookGenreService {

    private final BookGenreRepository bookGenreRepository;
    private final UnitOfWork unitOfWork;

    private final ModelMapper modelMapper;

    private final SaveGenreDtos saveGenreDtos;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;



    @Override
    public List<BookGenre> list() {
        return bookGenreRepository.findAll();
    }

  @Override
    public Optional<BookGenre> findById(Long id) {
        return bookGenreRepository.findById(id);
    }

    @Override
    public BookGenre saveBookGenre(SaveGenreDtos saveBookGenreDtos) {
        BookGenre bookGenre = modelMapper.map(saveBookGenreDtos, BookGenre.class);
        return bookGenreRepository.save(bookGenre);
    }

    @Override
    public BookGenreResponse updateBookGenre(Long id, SaveBookGenreDtos saveBookGenreDtos) {
        Optional<BookGenre> optionalBookGenre = bookGenreRepository.findById(id);
        if (optionalBookGenre.isPresent()) {
            BookGenre existingBookGenre = optionalBookGenre.get();
            modelMapper.map(saveBookGenreDtos, existingBookGenre);
            BookGenre updatedBookGenre = bookGenreRepository.save(existingBookGenre);
            BookGenreDtos bookDto = modelMapper.map(updatedBookGenre, BookGenreDtos.class);
            return new BookGenreResponse(String.valueOf(bookDto));
        } else {
            return new BookGenreResponse("Book not found");
        }
    }


@Override
    public BookGenreResponse delete(Long bookGenreId) {
        Optional<BookGenre> existingBookGenre = bookGenreRepository.findById(bookGenreId);
        if (!existingBookGenre.isPresent()) {
            return new BookGenreResponse("Book Genre not found.");
        }

        try {
            bookGenreRepository.delete(existingBookGenre.get());
            unitOfWork.complete();
            return new BookGenreResponse(existingBookGenre.get());
        } catch (Exception e) {
            return new BookGenreResponse("An error occurred while deleting the book: " + e.getMessage());
        }
    }

}
