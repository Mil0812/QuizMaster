package com.mil0812.persistence.repository.impl;

import com.mil0812.persistence.connection.ConnectionManager;
import com.mil0812.persistence.entity.impl.Result;
import com.mil0812.persistence.repository.GenericJdbcRepository;
import com.mil0812.persistence.repository.interfaces.ResultRepository;
import com.mil0812.persistence.repository.interfaces.TableTitles;
import com.mil0812.persistence.repository.mappers.RowMapper;
import com.mil0812.persistence.repository.mappers.impl.ResultRowMapper;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class ResultRepositoryImpl extends GenericJdbcRepository<Result>
implements ResultRepository {

  public ResultRepositoryImpl(ConnectionManager connectionManager,
      ResultRowMapper resultRowMapper) {
    super(connectionManager, resultRowMapper, TableTitles.RESULT.getName());
  }

  @Override
  protected List<String> tableAttributes() {
    return List.of("user", "test_id", "test_type", "section_id", "date_of_test", "grade");
  }

  @Override
  protected List<Object> tableValues(Result entity) {
    return null;
  }

  @Override
  public Optional<Result> findByTestId(UUID testId) {
    return findBy("test_id", testId);
  }

  @Override
  public Optional<Result> findBySectionId(UUID sectionId) {
    return findBy("section_id", sectionId);
  }

  @Override
  public Optional<Result> findByDate(Timestamp date) {
    return findBy("date_of_test", date);
  }
}