package com.mil0812.persistence.unitOfWork;

import com.mil0812.persistence.unitOfWork.impl.AnswerUnitOfWork;
import com.mil0812.persistence.unitOfWork.impl.QuestionUnitOfWork;
import com.mil0812.persistence.unitOfWork.impl.ResultUnitOfWork;
import com.mil0812.persistence.unitOfWork.impl.SectionUnitOfWork;
import com.mil0812.persistence.unitOfWork.impl.TestTypeUnitOfWork;
import com.mil0812.persistence.unitOfWork.impl.TestUnitOfWork;
import com.mil0812.persistence.unitOfWork.impl.UserUnitOfWork;
import org.springframework.stereotype.Component;


/**
 * Клас, в якому ми отримуємо залежності як сінглтони
 * і представляємо відкриті поля для них
 */
@Component
public class PersistenceContext {

  public final UserUnitOfWork users;
  public final SectionUnitOfWork sections;
  public final TestUnitOfWork tests;
  public final TestTypeUnitOfWork testTypes;
  public final QuestionUnitOfWork questions;
  public final AnswerUnitOfWork answers;
  public final ResultUnitOfWork results;

  public PersistenceContext(
      UserUnitOfWork userUnitOfWork,
      SectionUnitOfWork sectionUnitOfWork,
      TestUnitOfWork testUnitOfWork,
      TestTypeUnitOfWork testTypeUnitOfWork,
      QuestionUnitOfWork questionUnitOfWork,
      AnswerUnitOfWork answerUnitOfWork,
      ResultUnitOfWork resultUnitOfWork
  ) {
    this.users = userUnitOfWork;
    this.sections = sectionUnitOfWork;
    this.tests = testUnitOfWork;
    this.testTypes = testTypeUnitOfWork;
    this.questions = questionUnitOfWork;
    this.answers = answerUnitOfWork;
    this.results = resultUnitOfWork;
  }
}