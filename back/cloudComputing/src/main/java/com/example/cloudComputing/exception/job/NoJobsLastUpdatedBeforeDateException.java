package com.example.cloudComputing.exception.job;

public class NoJobsLastUpdatedBeforeDateException extends RuntimeException{
  public NoJobsLastUpdatedBeforeDateException(String message){
    super(message);
  }
}
