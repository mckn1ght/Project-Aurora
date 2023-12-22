package com.example.cloudComputing.exception.job;

public class NoJobsAfterDueDateException extends RuntimeException{
  public NoJobsAfterDueDateException(String message){
    super(message);
  }
}
