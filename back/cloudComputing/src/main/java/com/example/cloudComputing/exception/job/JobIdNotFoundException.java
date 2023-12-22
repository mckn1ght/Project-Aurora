package com.example.cloudComputing.exception.job;

public class JobIdNotFoundException extends RuntimeException{
  public JobIdNotFoundException(String message){
    super(message);
  }
}
