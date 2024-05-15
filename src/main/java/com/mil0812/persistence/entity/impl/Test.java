package com.mil0812.persistence.entity.impl;

import com.mil0812.persistence.entity.Entity;
import com.mil0812.persistence.entity.proxy.interfaces.Sections;
import com.mil0812.persistence.entity.proxy.interfaces.UserProxy;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public record Test(
    UUID id,
    UserProxy user,
    UUID userId,
    UUID testTypeId,
    UUID sectionId,
    String title,
    String image,
    int questionCount,
    Sections sections
) implements Entity, Comparable<Test> {

  public Test(UUID id, UserProxy user, UUID userId, UUID testTypeId, UUID sectionId, String title,
      String image,
      int questionCount, Sections sections) {
    this.id = id;
    this.user = user;
    this.userId = userId;
    this.testTypeId = testTypeId;
    this.sectionId = sectionId;
    this.title = title;
    this.image = image;
    this.questionCount = questionCount;
    this.sections = sections;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Test test = (Test) o;
    return questionCount == test.questionCount && Objects.equals(id, test.id)
        && Objects.equals(user, test.user) && Objects.equals(userId, test.userId)
        && Objects.equals(testTypeId, test.testTypeId) && Objects.equals(title,
        test.title) && Objects.equals(image, test.image) && Objects.equals(
        sections, test.sections);
  }


  public Set<Section> receiveSectionsLazy() {
    return sections.get(id);
  }

  public User receiveUserLazy() {
    return user.get(id);
  }


  @Override
  public int compareTo(Test t) {
    return this.title.compareTo(t.title);
  }

  @Override
  public String toString() {
    return STR."Test{id=\{id}, user=\{user}, userId=\{userId}, testTypeId=\{testTypeId}, sectionId=\{sectionId}, title='\{title}\{'\''}, image='\{image}\{'\''}, questionCount=\{questionCount}, sections=\{sections}\{'}'}";
  }
}
