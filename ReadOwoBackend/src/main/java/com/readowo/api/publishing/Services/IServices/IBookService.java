package com.readowo.api.publishing.Services.IServices;

import com.readowo.api.publishing.Dtos.SaveBookDtos;
import com.readowo.api.publishing.Dtos.SaveBookGenreDtos;
import com.readowo.api.publishing.Models.Book;
import com.readowo.api.publishing.Services.Communication.BookGenreResponse;
import com.readowo.api.publishing.Services.Communication.BookResponse;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<Book> getAllBooks();
    Optional<Book> getBookById(Long bookId);
    Book saveBook(SaveBookDtos saveBookDtos);

    void deleteBook(Long bookId);


    BookResponse updateBook(Long id, SaveBookDtos saveBookResource);

}
