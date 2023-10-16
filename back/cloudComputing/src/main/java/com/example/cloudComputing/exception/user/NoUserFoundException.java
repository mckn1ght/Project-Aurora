package com.example.cloudComputing.exception.user;

public class NoUserFoundException extends RuntimeException{
  public NoUserFoundException(String message){
    super(message);
  }
}
