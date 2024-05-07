package com.mil0812.persistence.entity.impl;

import com.mil0812.persistence.entity.Entity;
import com.mil0812.persistence.entity.proxy.Tests;
import java.sql.Date;
import java.util.Set;
import java.util.UUID;

/*
public record Student(UUID id, String firstName, String lastName,
                      Date dateOfBirth, UUID userId, User user, Tests tests)
    implements Entity, Comparable<Student>{

  public Set<Test> receiceTestsLazy(){
    return tests.get(id);
  }
  @Override
  public int compareTo(Student s) {
    return this.lastName.compareTo(s.lastName);
  }
}
*/
