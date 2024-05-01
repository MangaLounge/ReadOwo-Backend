package com.readowo.api.publishing.Services.ServicesImpl;

import com.readowo.api.Shared.Persistence.Repositories.UnitOfWork;

import com.readowo.api.publishing.Dtos.BookStatusDtos;
import com.readowo.api.publishing.Dtos.SaveBookStatusDtos;
import com.readowo.api.publishing.Models.BookStatus;
import com.readowo.api.publishing.Repositories.BookRepository;
import com.readowo.api.publishing.Repositories.BookStatusRepository;
import com.readowo.api.publishing.Services.Communication.BookStatusResponse;
import com.readowo.api.publishing.Services.IServices.IBookStatusService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Data
@AllArgsConstructor
public class BookStatusServiceImpl implements IBookStatusService {

    private final BookStatusRepository bookStatusRepository;
    private final UnitOfWork unitOfWork;

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;





    @Override
    public List<BookStatus> getAllBooksStatus() {
         return bookStatusRepository.findAll();
    }

    @Override
    public Optional<BookStatus> getBookStatusById(Long bookStatusId) {
         return bookStatusRepository.findById(bookStatusId);
    }

    @Override
    public BookStatus saveBookStatus(SaveBookStatusDtos saveBookStatusDtos) {
        BookStatus bookStatus = modelMapper.map(saveBookStatusDtos, BookStatus.class);
        return bookStatusRepository.save(bookStatus);
    }


    @Override
    public BookStatusResponse updateBookStatus(Long id, SaveBookStatusDtos saveBookStatusDtos) {
        Optional<BookStatus> optionalBook = bookStatusRepository.findById(id);
        if (optionalBook.isPresent()) {
            BookStatus existingBook = optionalBook.get();
            modelMapper.map(saveBookStatusDtos, existingBook);
            BookStatus updatedBook = bookStatusRepository.save(existingBook);
            BookStatusDtos bookResource = modelMapper.map(updatedBook, BookStatusDtos.class);
            return new BookStatusResponse(String.valueOf(bookResource));
        } else {
            return new BookStatusResponse("Book not found");
        }
    }

    @Override
    public BookStatusResponse deleteBookStatus(Long bookStatusId) {

        Optional<BookStatus> existingBookStatus = bookStatusRepository.findById(bookStatusId);
        if (!existingBookStatus.isPresent()) {
            return new BookStatusResponse("Book Genre not found.");
        }

        try {
            bookStatusRepository.delete(existingBookStatus.get());
            unitOfWork.complete();
            return new BookStatusResponse(existingBookStatus.get());
        } catch (Exception e) {
            return new BookStatusResponse("An error occurred while deleting the book: " + e.getMessage());
        }
    }
}
