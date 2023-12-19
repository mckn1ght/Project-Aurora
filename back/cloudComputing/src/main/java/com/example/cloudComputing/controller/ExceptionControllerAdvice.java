package com.example.cloudComputing.controller;

import com.example.cloudComputing.exception.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ExceptionControllerAdvice {
  @ExceptionHandler({NoCreditHigherFoundException.class, NoCreditLowerFoundException.class, NoUserFoundException.class,
    NoUserIsAdminFoundException.class, UserIdNotFoundException.class, UserUsernameNotFoundException.class,
    SQLIntegrityConstraintViolationException.class
  })
  public ResponseEntity<String> handleControllerExceptions(Exception exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> handle(MethodArgumentNotValidException e) {
    return ResponseEntity.badRequest()
      .body(e.getFieldError().getDefaultMessage());

  }
}
