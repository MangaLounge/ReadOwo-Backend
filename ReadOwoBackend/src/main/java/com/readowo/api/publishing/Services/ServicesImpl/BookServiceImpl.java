package com.readowo.api.publishing.Services.ServicesImpl;

import com.readowo.api.publishing.Dtos.BookDtos;
import com.readowo.api.publishing.Dtos.SaveBookDtos;
import com.readowo.api.publishing.Models.Book;
import com.readowo.api.publishing.Repositories.BookRepository;
import com.readowo.api.publishing.Services.Communication.BookResponse;
import com.readowo.api.publishing.Services.IServices.IBookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements IBookService {

    private final BookRepository bookRepository;


    private final ModelMapper modelMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book saveBook(SaveBookDtos saveBookDtos) {
        Book book = modelMapper.map(saveBookDtos, Book.class);
        return bookRepository.save(book);
    }

    @Override
    public BookResponse updateBook(Long id, SaveBookDtos saveBookResource) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            modelMapper.map(saveBookResource, existingBook);
            Book updatedBook = bookRepository.save(existingBook);
            BookDtos bookResource = modelMapper.map(updatedBook, BookDtos.class);
            return new BookResponse(String.valueOf(bookResource));
        } else {
            return new BookResponse("Book not found");
        }
    }


    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
