package com.example.cloudComputing.exception.job;

public class NoJobWithTitleContainingException extends RuntimeException{
  public NoJobWithTitleContainingException(String message){
    super(message);
  }
}
