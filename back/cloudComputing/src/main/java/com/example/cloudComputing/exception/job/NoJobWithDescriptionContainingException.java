package com.example.cloudComputing.exception.job;

public class NoJobWithDescriptionContainingException extends RuntimeException{
  public NoJobWithDescriptionContainingException(String message){
    super(message);
  }
}
