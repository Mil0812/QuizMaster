package com.mil0812.persistence.entity.proxy.impl;

import com.mil0812.persistence.entity.impl.Test;
import com.mil0812.persistence.entity.proxy.interfaces.TestProxy;
import com.mil0812.persistence.exception.EntityNotFound;
import com.mil0812.persistence.repository.interfaces.TestRepository;
import java.util.UUID;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TestProxyImpl implements TestProxy {
  private final ApplicationContext applicationContext;

  public TestProxyImpl(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Override
  public Test get(UUID entityId) {
    TestProxy proxy = (testId) -> applicationContext.getBean(TestRepository.class)
        .findById(testId)
        .orElseThrow(() -> new EntityNotFound("Не вдалось знайти тест за id..."));

    return proxy.get(entityId);
  }
}
