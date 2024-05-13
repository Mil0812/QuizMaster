package com.mil0812.domain.service.impl;

import com.mil0812.domain.exception.ImageNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class FileService {

  public Path getPathFromResource(String resourceName) {
    URL resourceUrl = getClass().getClassLoader().getResource(resourceName);
    if (resourceUrl == null) {
      throw new IllegalArgumentException(STR."Ресурс не знайдено: \{resourceName}...");
    }

    try {
      return Paths.get(resourceUrl.toURI());
    } catch (URISyntaxException e) {
      throw new RuntimeException(STR."Помилка з URI ресурсу: \{resourceUrl}", e);
    }
  }

  public byte[] getBytes(Path path) {
    byte[] fileBytes = null;
    try {
      if (Objects.nonNull(path)) {
        fileBytes = Files.readAllBytes(path);
      }
    } catch (IOException e) {
      throw new ImageNotFoundException();
    }
    return fileBytes;
  }
}