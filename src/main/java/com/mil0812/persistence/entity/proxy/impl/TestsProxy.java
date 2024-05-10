package com.mil0812.persistence.entity.proxy.impl;

import com.mil0812.persistence.entity.impl.Test;
import com.mil0812.persistence.entity.proxy.interfaces.Tests;
import com.mil0812.persistence.repository.interfaces.SectionRepository;
import com.mil0812.persistence.repository.interfaces.TestRepository;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TestsProxy implements Tests {
  private final ApplicationContext applicationContext;

  public TestsProxy(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Override
  public Set<Test> get(UUID sectionId) {
    var sectionRepository = applicationContext.getBean(SectionRepository.class);
    Tests tests = sId -> Collections.unmodifiableSet(sectionRepository.getTests(sId));
    return tests.get(sectionId);
  }
}
