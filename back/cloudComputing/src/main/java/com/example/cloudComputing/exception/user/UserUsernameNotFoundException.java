package com.example.cloudComputing.exception.user;

public class UserUsernameNotFoundException extends RuntimeException{
  public UserUsernameNotFoundException(String message){
    super(message);
  }
}
