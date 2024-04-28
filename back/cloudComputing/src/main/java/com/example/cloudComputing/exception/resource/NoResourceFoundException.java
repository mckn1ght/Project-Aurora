package com.example.cloudComputing.exception.resource;

public class NoResourceFoundException extends RuntimeException{
  public NoResourceFoundException(String message){
    super(message);
  }
}
