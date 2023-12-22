package com.example.cloudComputing.exception.job;

public class NoJobCreatedByUserException extends RuntimeException{
  public NoJobCreatedByUserException(String message){
    super(message);
  }
}
