package com.mil0812.persistence.entity.impl;

import com.mil0812.persistence.entity.Entity;
import java.sql.Date;
import java.util.UUID;

public record Student(UUID id, String firstName, String lastName,
                      Date dateOfBirth, UUID userId, User user)
    implements Entity, Comparable<Student>{


  @Override
  public int compareTo(Student s) {
    return this.lastName.compareTo(s.lastName);
  }
}
