package com.mil0812.persistence.repository.interfaces;

import org.springframework.stereotype.Component;

@Component
public enum TableTitles {
  USER("user"),
  TEST("test"),
  SECTION("section"),
  TEST_TYPE("test_type"),
  QUESTION("question"),
  ANSWER("answer"),
  RESULT("result"),
  SECTION_TEST("section_test");

  private final String name;

  TableTitles(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
