package com.mil0812.persistence.entity.impl;

import com.mil0812.persistence.entity.Entity;
import java.awt.Image;
import java.util.UUID;

public record Question(UUID id, UUID typeId, QuestionType type, Image image,
                       UUID testId, Test test, String questionText)
implements Entity, Comparable<Question> {


  @Override
  public int compareTo(Question q) {
    return this.questionText.compareTo(q.questionText);
  }

}
