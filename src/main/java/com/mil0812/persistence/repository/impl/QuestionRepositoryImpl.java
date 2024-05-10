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
import java.util.List;
import java.util.Optional;
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
  protected List<String> tableAttributes() {
    return List.of("type", "test_id", "question_text");
  }

  @Override
  protected List<Object> tableValues(Question entity) {
    return null;
  }

  @Override
  public Optional<Question> findByTestId(UUID testId) {
    return findBy("type", testId);
  }
}
