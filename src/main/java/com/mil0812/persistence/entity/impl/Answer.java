package com.mil0812.persistence.entity.impl;

import com.mil0812.persistence.entity.Entity;
import java.util.UUID;

public record Answer(
    UUID id,
    UUID questionId,
    String answerText,
    boolean correctness
) implements Entity, Comparable<Answer> {

  @Override
  public int compareTo(Answer a) {
    return this.answerText.compareTo(a.answerText);
  }
}
