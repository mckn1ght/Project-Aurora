package com.example.cloudComputing.exception.review;

public class NoReviewFoundException extends RuntimeException{
  public NoReviewFoundException(String message){
    super(message);
  }
}
