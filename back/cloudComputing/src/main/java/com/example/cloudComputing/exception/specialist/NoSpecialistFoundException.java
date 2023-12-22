package com.example.cloudComputing.exception.specialist;

public class NoSpecialistFoundException extends RuntimeException{
  public NoSpecialistFoundException(String message){
    super(message);
  }
}
