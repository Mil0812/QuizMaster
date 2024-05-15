package com.mil0812.persistence.unitOfWork.impl;

import com.mil0812.persistence.entity.impl.Test;
import com.mil0812.persistence.repository.interfaces.TestRepository;
import com.mil0812.persistence.unitOfWork.GeneralUnitOfWork;
import org.springframework.stereotype.Component;

@Component
public class TestUnitOfWork extends GeneralUnitOfWork<Test> {

  public final TestRepository testRepository;

  protected TestUnitOfWork(TestRepository testRepository) {
    super(testRepository);
    this.testRepository = testRepository;
  }
}
