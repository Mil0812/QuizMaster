package com.mil0812.persistence.entity.impl;

import com.mil0812.persistence.entity.Entity;
import java.awt.Image;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/*
public record Test(UUID id, String title, UUID sectionId, UUID teacherId,
                   Section section, Image image, Students students) //TODO: Proxy
    implements Entity, Comparable<Test> {

  public Set<Student> receiveStudentsLazy(){
    return students.get(id);
  }

  @Override
  public boolean equals(Object obj) {
    if(this == obj){
      return true;
    }
    if(obj == null || getClass() != obj.getClass()){
      return false;
    }
    Test test = (Test) obj;
    return Objects.equals(id, test.id) && Objects.equals(title, test.title) &&
        Objects.equals(sectionId, test.sectionId) && Objects.equals(teacherId, test.teacherId)
        && Objects.equals(section, test.section) && Objects.equals(image, test.image)
        && Objects.equals(students, test.students);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, sectionId, teacherId, section, image, students);
  }

  public Set<Student> receiveStudentsLazy(){
    return students.get(id);
  }

  @Override
  public int compareTo(Test t) {
    return this.title.compareTo(t.title);
  }
}*/
