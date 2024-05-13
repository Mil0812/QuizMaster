package com.mil0812.persistence.entity.impl;

import com.mil0812.persistence.entity.Entity;
import com.mil0812.persistence.entity.proxy.interfaces.TestProxy;
import java.util.UUID;

public record Question(
    UUID id,
    TestProxy test,
    UUID testId,
    String questionText
) implements Entity, Comparable<Question> {


  @Override
  public int compareTo(Question q) {
    return this.questionText.compareTo(q.questionText);
  }

}
