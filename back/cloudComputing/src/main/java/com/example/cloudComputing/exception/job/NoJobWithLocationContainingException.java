package com.example.cloudComputing.exception.job;

public class NoJobWithLocationContainingException extends RuntimeException{
  public NoJobWithLocationContainingException(String message){
    super(message);
  }
}
