package com.mil0812.domain.exception;

public class ImageNotFoundException extends RuntimeException {

  public ImageNotFoundException() {
    super("Помилка при збереженні файлу картинки");
  }
}
