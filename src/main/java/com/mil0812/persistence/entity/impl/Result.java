package com.mil0812.persistence.entity.impl;

import com.mil0812.persistence.entity.Entity;
import java.sql.Date;
import java.util.UUID;

public record Result(UUID id, UUID userId, UUID testId,
                     User user, Test test, Date dateOfTest, int grade)
implements Entity, Comparable<Result> {


  @Override
  public int compareTo(Result r) {
    return this.testId.compareTo(r.testId);
  }
}
