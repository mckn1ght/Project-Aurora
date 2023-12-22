package com.example.cloudComputing.exception.job;

public class NoJobsCreatedAfterDateException extends RuntimeException{
  public NoJobsCreatedAfterDateException(String message){
    super(message);
  }
}
