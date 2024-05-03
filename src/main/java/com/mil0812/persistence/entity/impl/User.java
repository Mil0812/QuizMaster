package com.mil0812.persistence.entity.impl;

public class User {
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
