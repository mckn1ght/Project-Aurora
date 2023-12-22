package com.example.cloudComputing.exception.job;

public class NoJobsOnWorkflowStepException extends RuntimeException{
  public NoJobsOnWorkflowStepException(String message){
    super(message);
  }
}
