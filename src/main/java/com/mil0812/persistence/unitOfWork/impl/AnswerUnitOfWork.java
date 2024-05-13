package com.mil0812.persistence.unitOfWork.impl;

import com.mil0812.persistence.entity.impl.Answer;
import com.mil0812.persistence.entity.impl.TestType;
import com.mil0812.persistence.repository.Repository;
import com.mil0812.persistence.repository.interfaces.AnswerRepository;
import com.mil0812.persistence.unitOfWork.GeneralUnitOfWork;
import org.springframework.stereotype.Component;

@Component
public class AnswerUnitOfWork extends GeneralUnitOfWork<Answer> {

  public AnswerUnitOfWork(AnswerRepository answerRepository) {
    super(answerRepository);
  }
}
