package com.mil0812.persistence.repository.impl;

import com.mil0812.persistence.connection.ConnectionManager;
import com.mil0812.persistence.entity.impl.Test;
import com.mil0812.persistence.entity.impl.TestType;
import com.mil0812.persistence.repository.GenericJdbcRepository;
import com.mil0812.persistence.repository.interfaces.TableTitles;
import com.mil0812.persistence.repository.interfaces.TestRepository;
import com.mil0812.persistence.repository.interfaces.TestTypeRepository;
import com.mil0812.persistence.repository.mappers.RowMapper;
import com.mil0812.persistence.repository.mappers.impl.TestTypeRowMapper;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.stereotype.Repository;

@Repository
public class TestTypeRepositoryImpl extends GenericJdbcRepository<TestType>
    implements TestTypeRepository {

  public TestTypeRepositoryImpl(
      ConnectionManager connectionManager,
      TestTypeRowMapper testTypeRowMapper) {
    super(connectionManager, testTypeRowMapper, TableTitles.TEST_TYPE.getName());
  }

  @Override
  protected Map<String, Object> tableValues(TestType testType) {
    Map<String, Object> values = new LinkedHashMap<>();

    if (!testType.name().isBlank()) {
      values.put("name", testType.name());
    }
    if (!testType.description().isBlank()) {
      values.put("description", testType.description());
    }
    if (!testType.title().isBlank()) {
      values.put("title", testType.title());
    }
    if (!testType.image().isBlank()) {
      values.put("image", testType.image());
    }
    values.put("max_answer_count", testType.maxAnswerCount());
    values.put("correct_answer_count", testType.correctAnswerCount());
    return values;
  }
}
