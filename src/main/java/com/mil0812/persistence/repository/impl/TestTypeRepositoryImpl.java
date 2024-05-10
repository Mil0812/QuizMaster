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
import java.util.List;
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
  protected List<String> tableAttributes() {
    return List.of("name", "description", "title", "image", "max_answer_count", "correct_answer_count");
  }

  @Override
  protected List<Object> tableValues(TestType entity) {
    return null;
  }
}
