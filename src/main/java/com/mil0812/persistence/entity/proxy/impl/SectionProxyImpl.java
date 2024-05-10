package com.mil0812.persistence.entity.proxy.impl;

import com.mil0812.persistence.entity.impl.Section;
import com.mil0812.persistence.entity.proxy.interfaces.SectionProxy;
import com.mil0812.persistence.exception.EntityNotFound;
import com.mil0812.persistence.repository.interfaces.SectionRepository;
import java.util.UUID;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SectionProxyImpl implements SectionProxy {
  private final ApplicationContext applicationContext;

  public SectionProxyImpl(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Override
  public Section get(UUID entityId) {
    SectionProxy proxy = (sectionId) -> applicationContext.getBean(SectionRepository.class)
        .findById(sectionId)
        .orElseThrow(() -> new EntityNotFound("Не вдалось знайти розділ за id..."));

    return proxy.get(entityId);
  }
}
