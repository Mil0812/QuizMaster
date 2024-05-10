package com.mil0812.persistence.repository.impl;

import com.mil0812.persistence.connection.ConnectionManager;
import com.mil0812.persistence.entity.impl.Answer;
import com.mil0812.persistence.repository.GenericJdbcRepository;
import com.mil0812.persistence.repository.interfaces.AnswerRepository;
import com.mil0812.persistence.repository.interfaces.TableTitles;
import com.mil0812.persistence.repository.mappers.RowMapper;
import com.mil0812.persistence.repository.mappers.impl.AnswerRowMapper;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerRepositoryImpl extends GenericJdbcRepository<Answer>
implements AnswerRepository {

  public AnswerRepositoryImpl(ConnectionManager connectionManager,
      AnswerRowMapper answerRowMapper) {
    super(connectionManager, answerRowMapper, TableTitles.ANSWER.getName());
  }

  @Override
  protected List<String> tableAttributes() {
    return List.of("question_id", "answer_text", "correctness");
  }

  @Override
  protected List<Object> tableValues(Answer entity) {
    return null;
  }

  @Override
  public Optional<Answer> findByQuestionId(UUID questionId) {
    return findBy("question_id", questionId);
  }
}
