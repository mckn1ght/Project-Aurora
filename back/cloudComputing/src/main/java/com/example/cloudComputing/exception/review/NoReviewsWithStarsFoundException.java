package com.example.cloudComputing.exception.review;

public class NoReviewsWithStarsFoundException extends RuntimeException{
  public NoReviewsWithStarsFoundException(String message){
    super(message);
  }
}
