package com.example.cloudComputing.exception.job;

public class NoJobsCreatedOnDateException extends RuntimeException{
  public NoJobsCreatedOnDateException(String message){
    super(message);
  }
}
