package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.BookDto;
import com.example.cloudComputing.entity.Book;

import java.util.List;
public interface BookService {
    BookDto addBook(BookDto bookDto);
    List<BookDto> findAll();
    Book save(Book book);

    BookDto getById(Long id);


}
