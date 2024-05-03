package com.mil0812.persistence.entity.impl;

import com.mil0812.persistence.entity.Entity;
import java.util.UUID;

public record User(UUID id, String login, String password, String status)
implements Entity, Comparable<User> {

  //за чим сортує
  @Override
  public int compareTo(User u) {
    return this.login.compareTo(u.login);
  }

  public enum Status{
    TEACHER,
    STUDENT;
  }
}
