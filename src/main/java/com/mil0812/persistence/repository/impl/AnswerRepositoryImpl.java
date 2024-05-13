package com.mil0812.persistence.repository.impl;

import com.mil0812.persistence.connection.ConnectionManager;
import com.mil0812.persistence.entity.impl.Answer;
import com.mil0812.persistence.repository.GenericJdbcRepository;
import com.mil0812.persistence.repository.interfaces.AnswerRepository;
import com.mil0812.persistence.repository.interfaces.TableTitles;
import com.mil0812.persistence.repository.mappers.RowMapper;
import com.mil0812.persistence.repository.mappers.impl.AnswerRowMapper;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
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
  public Optional<Answer> findByQuestionId(UUID questionId) {
    return findBy("question_id", questionId);
  }

  public Set<Answer> findAllByQuestionId(UUID questionId) {
    return findAllWhere(STR."question_id = \{questionId}");
  }

  @Override
  protected Map<String, Object> tableValues(Answer answer) {
    Map<String, Object> values = new LinkedHashMap<>();

    if (Objects.nonNull(answer.questionId())) {
      values.put("question_id", answer.questionId());
    }
    if (!answer.answerText().isBlank()) {
      values.put("answer_text", answer.answerText());
    }
    return values;
  }
}
