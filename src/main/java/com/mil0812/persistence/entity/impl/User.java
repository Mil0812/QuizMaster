package com.mil0812.persistence.entity.impl;

import com.mil0812.persistence.entity.Entity;
import java.util.Objects;
import java.util.UUID;

public record User(UUID id, String login, String password, Status status)
implements Entity, Comparable<User> {

  //Метод equals, який перевіряє, чи об’єкти класу User рівні один одному
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(id, user.id) && status == user.status && Objects.equals(
        login, user.login) && Objects.equals(password, user.password);
  }

  // Метод Objects.hash(id, login, password, status) об’єднує значення цих полів і обчислює хеш-код
  @Override
  public int hashCode() {
    return Objects.hash(id, login, password, status);
  }


  //за чим сортує
  @Override
  public int compareTo(User u) {
    return this.login.compareTo(u.login);
  }

  public enum Status{
    TEACHER("teacher"),
    STUDENT("student");

    String name;
    Status(String name){
      this.name = name;
    }

    public String getName() {
      return name;
    }
  }
}
