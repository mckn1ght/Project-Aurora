package com.example.cloudComputing.exception.review;

public class NoReviewsReceivedBySpecialistIdException extends RuntimeException{
  public NoReviewsReceivedBySpecialistIdException(String message){
    super(message);
  }
}
