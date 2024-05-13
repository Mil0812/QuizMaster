package com.mil0812.persistence.entity.impl;

import com.mil0812.persistence.entity.Entity;
import com.mil0812.persistence.entity.proxy.interfaces.SectionProxy;
import com.mil0812.persistence.entity.proxy.interfaces.TestProxy;
import com.mil0812.persistence.entity.proxy.interfaces.UserProxy;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

public record Result(
    UUID id,
    UserProxy user,
    UUID userId,
    TestProxy test,
    UUID testId,
    SectionProxy section,
    UUID sectionId,
    int grade,
    LocalDateTime dateOfTest
)
    implements Entity, Comparable<Result> {

  @Override
  public int compareTo(Result r) {
    return this.testId.compareTo(r.testId);
  }
}
