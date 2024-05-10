package com.mil0812.persistence.entity.impl;

import com.mil0812.persistence.entity.Entity;
import com.mil0812.persistence.entity.proxy.impl.SectionProxyImpl;
import com.mil0812.persistence.entity.proxy.impl.TestProxyImpl;
import com.mil0812.persistence.entity.proxy.impl.UserProxyImpl;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

//TODO: SectionProxy and UserProxy
public record Result(
    UUID id,
    UserProxyImpl user,
    UUID userId,
    TestProxyImpl test,
    UUID testId,
    SectionProxyImpl section,
    UUID sectionId,
    LocalDateTime dateOfTest,
    double grade
)
implements Entity, Comparable<Result> {

  @Override
  public int compareTo(Result r) {
    return this.testId.compareTo(r.testId);
  }
}
