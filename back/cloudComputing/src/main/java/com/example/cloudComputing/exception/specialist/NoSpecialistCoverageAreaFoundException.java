package com.example.cloudComputing.exception.specialist;

public class NoSpecialistCoverageAreaFoundException extends RuntimeException{
  public NoSpecialistCoverageAreaFoundException(String message){
    super(message);
  }
}
