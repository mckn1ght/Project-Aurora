package com.example.cloudComputing.exception.review;

public class NoReviewsWithStarsSmallerFoundException extends RuntimeException{
  public NoReviewsWithStarsSmallerFoundException(String message){
    super(message);
  }
}
