package com.mil0812.persistence.entity.impl;

import com.mil0812.persistence.entity.Entity;
import java.util.UUID;

public record QuestionType(UUID id, String type)
    implements Entity, Comparable<QuestionType> {

  @Override
  public int compareTo(QuestionType qt) {
    return this.type.compareTo(qt.type);
  }
}
