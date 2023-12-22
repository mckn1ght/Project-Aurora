package com.example.cloudComputing.exception.review;

public class NoReviewsWithStarsAfterFoundException extends RuntimeException{
  public NoReviewsWithStarsAfterFoundException(String message){
    super(message);
  }
}
