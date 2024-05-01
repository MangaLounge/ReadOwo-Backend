package com.readowo.api.publishing.Services.IServices;

import com.readowo.api.publishing.Dtos.SaveBookDtos;
import com.readowo.api.publishing.Dtos.SaveBookStatusDtos;
import com.readowo.api.publishing.Models.Book;
import com.readowo.api.publishing.Models.BookStatus;
import com.readowo.api.publishing.Services.Communication.BookResponse;
import com.readowo.api.publishing.Services.Communication.BookStatusResponse;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface IBookStatusService {

    List<BookStatus> getAllBooksStatus();
    Optional<BookStatus> getBookStatusById(Long bookId);
    BookStatus saveBookStatus(SaveBookStatusDtos saveBookStatusDtos);

    BookStatusResponse deleteBookStatus(Long bookStatusId);

    BookStatusResponse updateBookStatus(Long id, SaveBookStatusDtos saveBookStatusDtos);

}
