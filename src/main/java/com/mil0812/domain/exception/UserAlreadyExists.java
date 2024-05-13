package com.mil0812.domain.exception;

public class UserAlreadyExists extends RuntimeException {

  public UserAlreadyExists(String message) {
    super(message);
  }
}
