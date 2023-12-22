package com.example.cloudComputing.exception.review;

public class NoReviewIdFoundException extends RuntimeException{
  public NoReviewIdFoundException(String message){
    super(message);
  }
}
