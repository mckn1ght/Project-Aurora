package com.example.cloudComputing.exception.user;

public class NoCreditHigherFoundException extends RuntimeException{
  public NoCreditHigherFoundException(String message){
    super(message);
  }
}
