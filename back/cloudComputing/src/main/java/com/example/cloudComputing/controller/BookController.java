package com.example.cloudComputing.controller;

import com.example.cloudComputing.dto.BookDto;
import com.example.cloudComputing.entity.Book;
import com.example.cloudComputing.mapper.BookMapper;
import com.example.cloudComputing.repository.BookRepository;
import com.example.cloudComputing.serviceImpl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookServiceImpl bookServiceImpl;

    @Autowired
    BookMapper bookMapper;

    @PostMapping("/add")
    public ResponseEntity<BookDto> addbook(@RequestBody BookDto bookDto){
        return ResponseEntity.ok(bookServiceImpl.addBook(bookDto));
    }

    @RequestMapping("/getById/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(bookServiceImpl.getById(id));
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public List<BookDto> getAll(){
        return bookServiceImpl.findAll();
    }
}
