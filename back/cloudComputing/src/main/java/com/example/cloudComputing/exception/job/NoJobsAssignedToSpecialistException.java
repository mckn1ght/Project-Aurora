package com.example.cloudComputing.exception.job;

public class NoJobsAssignedToSpecialistException extends RuntimeException{
  public NoJobsAssignedToSpecialistException(String message){
    super(message);
  }
}
