package com.mil0812.persistence.entity.impl;

import com.mil0812.persistence.entity.Entity;
import java.util.UUID;

public record Teacher(UUID id, String firstName, String lastName,
                      String patronymic, String email, UUID userId, User user)
implements Entity, Comparable<Teacher> {

  @Override
  public int compareTo(Teacher t) {
    return this.lastName.compareTo(t.lastName);
  }
}
