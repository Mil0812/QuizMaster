package com.mil0812.persistence.repository.impl;

import com.mil0812.persistence.connection.ConnectionManager;
import com.mil0812.persistence.entity.impl.Question;
import com.mil0812.persistence.entity.impl.TestType;
import com.mil0812.persistence.repository.GenericJdbcRepository;
import com.mil0812.persistence.repository.interfaces.QuestionRepository;
import com.mil0812.persistence.repository.interfaces.TableTitles;
import com.mil0812.persistence.repository.interfaces.TestTypeRepository;
import com.mil0812.persistence.repository.mappers.RowMapper;
import com.mil0812.persistence.repository.mappers.impl.QuestionRowMapper;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionRepositoryImpl extends GenericJdbcRepository<Question>
    implements QuestionRepository {

  public QuestionRepositoryImpl(
      ConnectionManager connectionManager,
      QuestionRowMapper questionRowMapper) {
    super(connectionManager, questionRowMapper, TableTitles.QUESTION.getName());
  }

  @Override
  public Optional<Question> findByTestId(UUID testId) {
    return findBy("test_id", testId);
  }

  @Override
  public Set<Question> findAllByTestId(UUID testId) {
    return findAllWhere(STR."test_id = \{testId}");
  }

  @Override
  protected Map<String, Object> tableValues(Question question) {
    Map<String, Object> values = new LinkedHashMap<>();

    if (!question.questionText().isBlank()) {
      values.put("question_text", question.questionText());
    }
    if (Objects.nonNull(question.testId())) {
      values.put("test_id", question.testId());
    }
    return values;
  }
}
