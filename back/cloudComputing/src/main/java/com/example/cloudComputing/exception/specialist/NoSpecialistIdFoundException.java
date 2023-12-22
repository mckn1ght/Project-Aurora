package com.example.cloudComputing.exception.specialist;

public class NoSpecialistIdFoundException extends RuntimeException{
  public NoSpecialistIdFoundException(String message){
    super(message);
  }
}
