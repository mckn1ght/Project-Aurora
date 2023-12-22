package com.example.cloudComputing.exception.job;

public class NoJobsBeforeDueDateException extends RuntimeException{
  public NoJobsBeforeDueDateException(String message){
    super(message);
  }
}
