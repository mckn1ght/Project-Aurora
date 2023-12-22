package com.example.cloudComputing.exception.job;

public class NoJobsLastUpdatedOnDateException extends RuntimeException{
  public NoJobsLastUpdatedOnDateException(String message){
    super(message);
  }
}
