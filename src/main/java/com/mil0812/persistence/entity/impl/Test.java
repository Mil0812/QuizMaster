package com.mil0812.persistence.entity.impl;

import com.mil0812.persistence.entity.Entity;
import java.awt.Image;
import java.util.Set;
import java.util.UUID;

//Set - багато до багатьох
public record Test(UUID id, String title, UUID sectionId, UUID teacherId,
                   Section section, Image image, Set<Student> students)
    implements Entity, Comparable<Test> {

  @Override
  public int compareTo(Test t) {
    return this.title.compareTo(t.title);
  }
}