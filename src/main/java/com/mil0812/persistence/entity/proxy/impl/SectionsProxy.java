package com.mil0812.persistence.entity.proxy.impl;

import com.mil0812.persistence.entity.impl.Section;
import com.mil0812.persistence.entity.proxy.interfaces.Sections;
import com.mil0812.persistence.entity.proxy.interfaces.Tests;
import com.mil0812.persistence.repository.interfaces.SectionRepository;
import com.mil0812.persistence.repository.interfaces.TestRepository;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SectionsProxy implements Sections {
  private final ApplicationContext applicationContext;

  public SectionsProxy(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }


  @Override
  public Set<Section> get(UUID testId) {
    var testRepository = applicationContext.getBean(TestRepository.class);
    Sections sections = tId -> Collections.unmodifiableSet(testRepository.getSections(tId));
    return sections.get(testId);
  }
}
