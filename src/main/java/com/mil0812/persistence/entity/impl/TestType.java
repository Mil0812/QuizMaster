package com.mil0812.persistence.entity.impl;

import com.mil0812.persistence.entity.Entity;
import java.awt.Image;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public record TestType(
    UUID id,
    String name,
    String description,
    String title,
    byte[] image,
    int maxAnswerCount,
    int correctAnswerCount
) implements Entity, Comparable<TestType> {

  public TestType(UUID id, String name, String description, String title, byte[] image,
      int maxAnswerCount, int correctAnswerCount) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.title = title;
    this.image = image;
    this.maxAnswerCount = maxAnswerCount;
    this.correctAnswerCount = correctAnswerCount;
  }


  @Override
  public int hashCode() {
    int result = Objects.hash(id, name, description, title, maxAnswerCount, correctAnswerCount);
    result = 31 * result + Arrays.hashCode(image);
    return result;
  }

  @Override
  public int compareTo(TestType tt) {
    return this.name.compareTo(tt.name);
  }

  @Override
  public String toString() {
    return STR."TestType{id=\{id}, name='\{name}\{'\''}, description='\{description}\{'\''}, title='\{title}\{'\''}, image=\{Arrays.toString(
        image)}, maxAnswerCount=\{maxAnswerCount}, correctAnswerCount=\{correctAnswerCount}\{'}'}";
  }
}