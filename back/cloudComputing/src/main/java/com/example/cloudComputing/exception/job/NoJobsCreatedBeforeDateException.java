package com.example.cloudComputing.exception.job;

public class NoJobsCreatedBeforeDateException extends RuntimeException{
  public NoJobsCreatedBeforeDateException(String message){
    super(message);
  }
}
