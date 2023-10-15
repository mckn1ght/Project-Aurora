package com.example.cloudComputing.repository;

import com.example.cloudComputing.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
  Book save(Book book);

  List<Book> findByTitle(String title);

  List<Book> findAll();

  Book getById(Long id);
}
