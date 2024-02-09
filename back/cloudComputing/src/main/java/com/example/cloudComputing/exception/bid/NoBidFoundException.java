package com.example.cloudComputing.exception.bid;

public class NoBidFoundException extends RuntimeException{
  public NoBidFoundException(String message){
    super(message);
  }
}
