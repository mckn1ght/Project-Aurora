package com.example.cloudComputing.exception.user;

public class UserIdNotFoundException extends RuntimeException {

  public UserIdNotFoundException(String message){
    super(message);
  }

}
