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
    String image,
    int maxAnswerCount,
    int correctAnswerCount
) implements Entity, Comparable<TestType> {

  public TestType(UUID id, String name, String description, String title, String image,
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
  public int compareTo(TestType tt) {
    return this.name.compareTo(tt.name);
  }

  @Override
  public String toString() {
    return STR."TestType{id=\{id}, name='\{name}\{'\''}, description='\{description}\{'\''}, title='\{title}\{'\''}, image='\{image}\{'\''}, maxAnswerCount=\{maxAnswerCount}, correctAnswerCount=\{correctAnswerCount}\{'}'}";
  }
}