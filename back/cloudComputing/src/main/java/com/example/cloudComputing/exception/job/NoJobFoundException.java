package com.example.cloudComputing.exception.job;

public class NoJobFoundException extends RuntimeException{
  public NoJobFoundException(String message){
    super(message);
  }
}
