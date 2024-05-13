package com.mil0812.domain.exception;

public class SignInException extends RuntimeException {

  public SignInException(String message) {
    super("Неправильний логін або пароль...");
  }
}
