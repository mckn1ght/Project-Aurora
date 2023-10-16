package com.example.cloudComputing.exception.user;

public class NoUserPermissionFoundException extends RuntimeException{
  public NoUserPermissionFoundException(String message){
    super(message);
  }
}
