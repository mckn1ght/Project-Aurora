package com.example.cloudComputing.serviceImpl;

import com.example.cloudComputing.dto.BookDto;
import com.example.cloudComputing.entity.Book;
import com.example.cloudComputing.mapper.BookMapper;
import com.example.cloudComputing.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private BookMapper bookMapper;

  public BookDto addBook(BookDto bookDto) {
    return bookMapper.mapToBookDto(bookRepository.save(bookMapper.mapToBook(bookDto)));
  }

  public List<BookDto> findAll() {
    List<BookDto> bookDtos = bookRepository.findAll().stream().map(book -> bookMapper.mapToBookDto(book)).collect(Collectors.toList());
    if (bookDtos.isEmpty()) {
      throw new NoSuchElementException("No books found");
    }
    return bookDtos;
  }

  public BookDto getById(Long Id) {
    Book book = bookRepository.getById(Id);
    if (book == null) {
      throw new NoSuchElementException("Id does not exist");
    }
    return bookMapper.mapToBookDto(book);
  }

  @Override
  public Book save(Book book) {
    return bookRepository.save(book);
  }

}
