package com.example.cloudComputing.mapper;

import com.example.cloudComputing.dto.BookDto;
import com.example.cloudComputing.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
  public Book mapToBook(BookDto bookDto) {
    return Book.builder()
      .id(bookDto.getId())
      .title(bookDto.getTitle())
      .author(bookDto.getAuthor())
      .pages(bookDto.getPages())
      .build();
  }

  public BookDto mapToBookDto(Book book) {
    return BookDto.builder()
      .id(book.getId())
      .title(book.getTitle())
      .author(book.getAuthor())
      .pages(book.getPages())
      .build();
  }
}
