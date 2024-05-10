package com.mil0812.persistence.entity.impl;

import com.mil0812.persistence.entity.Entity;
import com.mil0812.persistence.entity.proxy.impl.TestsProxy;
import java.util.Set;
import java.util.UUID;

public record Section(
    UUID id,
    String name,
    TestsProxy tests
) implements Entity, Comparable<Section>{

public Set<Test> receiveTestsLazy(){
  return tests.get(id);
}
  @Override
  public int compareTo(Section st) {
    return this.name.compareTo(st.name);
  }
}

