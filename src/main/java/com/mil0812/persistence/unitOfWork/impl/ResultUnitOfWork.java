package com.mil0812.persistence.unitOfWork.impl;

import com.mil0812.persistence.entity.impl.Result;
import com.mil0812.persistence.repository.interfaces.ResultRepository;
import com.mil0812.persistence.unitOfWork.GeneralUnitOfWork;
import org.springframework.stereotype.Component;

@Component
public class ResultUnitOfWork extends GeneralUnitOfWork<Result> {

  private final ResultRepository repository;

  protected ResultUnitOfWork(ResultRepository resultRepository) {
    super(resultRepository);
    this.repository = resultRepository;
  }
}
