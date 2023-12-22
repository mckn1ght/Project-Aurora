package com.example.cloudComputing.exception.job;

public class NoJobsLastUpdatedAfterDateException extends RuntimeException{
  public NoJobsLastUpdatedAfterDateException(String message){
    super(message);
  }
}
