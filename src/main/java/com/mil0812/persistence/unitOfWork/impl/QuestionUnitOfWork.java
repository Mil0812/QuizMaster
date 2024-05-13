package com.mil0812.persistence.unitOfWork.impl;

import com.mil0812.persistence.entity.impl.Question;
import com.mil0812.persistence.entity.impl.TestType;
import com.mil0812.persistence.repository.Repository;
import com.mil0812.persistence.repository.interfaces.QuestionRepository;
import com.mil0812.persistence.unitOfWork.GeneralUnitOfWork;
import org.springframework.stereotype.Component;

@Component
public class QuestionUnitOfWork extends GeneralUnitOfWork<Question> {

  public QuestionUnitOfWork(QuestionRepository questionRepository) {
    super(questionRepository);
  }
}
