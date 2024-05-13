package com.mil0812.persistence.unitOfWork.impl;

import com.mil0812.persistence.entity.impl.TestType;
import com.mil0812.persistence.repository.interfaces.TestTypeRepository;
import com.mil0812.persistence.unitOfWork.GeneralUnitOfWork;
import org.springframework.stereotype.Component;

@Component
public class TestTypeUnitOfWork extends GeneralUnitOfWork<TestType> {

  private final TestTypeRepository repository;

  protected TestTypeUnitOfWork(TestTypeRepository testTypeRepository) {
    super(testTypeRepository);
    this.repository = testTypeRepository;
  }
}
