package com.example.cloudComputing.exception.user;

public class NoUserIsAdminFoundException extends RuntimeException{
  public NoUserIsAdminFoundException(String message){
    super(message);
  }
}
