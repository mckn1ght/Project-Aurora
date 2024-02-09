package com.example.cloudComputing.exception.bid;

public class NoBidIdFoundException extends RuntimeException{
  public NoBidIdFoundException(String message){
    super(message);
  }
}
