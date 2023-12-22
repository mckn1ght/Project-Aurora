package com.example.cloudComputing.exception.job;

public class NoJobsWithDueDateException extends RuntimeException{
  public NoJobsWithDueDateException(String message){
    super(message);
  }
}
