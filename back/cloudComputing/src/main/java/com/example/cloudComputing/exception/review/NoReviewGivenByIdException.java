package com.example.cloudComputing.exception.review;

public class NoReviewGivenByIdException extends RuntimeException{
  public NoReviewGivenByIdException(String message){
    super(message);
  }
}
