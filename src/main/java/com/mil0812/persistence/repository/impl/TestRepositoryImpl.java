package com.mil0812.persistence.repository.impl;

import com.mil0812.persistence.connection.ConnectionManager;
import com.mil0812.persistence.entity.impl.Section;
import com.mil0812.persistence.entity.impl.Test;
import com.mil0812.persistence.repository.GenericJdbcRepository;
import com.mil0812.persistence.repository.interfaces.TableTitles;
import com.mil0812.persistence.repository.interfaces.TestRepository;
import com.mil0812.persistence.repository.mappers.impl.TestRowMapper;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepositoryImpl extends GenericJdbcRepository<Test>
    implements TestRepository {

  public TestRepositoryImpl(ConnectionManager connectionManager,
      TestRowMapper testRowMapper) {
    super(connectionManager, testRowMapper, TableTitles.TEST.getName());
  }

  @Override
  protected List<String> tableAttributes() {
    return List.of("user", "testTypeId", "title", "image", "questionCount", "sections");
  }

  @Override
  protected List<Object> tableValues(Test entity) {
    return null;
  }

  @Override
  public boolean attach(UUID sectionId, UUID testId) {
    final String sql =
        """
            INSERT INTO section_test(section_id, test_it)
            VALUES (?, ?);
            """;
    return false;
  }

  @Override
  public boolean detach(UUID sectionId, UUID testId) {
    final String sql =
        """
            DELETE FROM section_test
                  WHERE section_id = ? AND test_id = ?;
            """;
    return false;
  }
  @Override
  public Optional<Test> findByAuthor(UUID userId) {
    return findBy("user", userId);
  }

  @Override
  public Optional<Test> findByTestType(UUID testTypeId) {
    return findBy("test_type_id", testTypeId);
  }

  @Override
  public Set<Section> getSections(UUID testId) {
    final String sql =
        """
            SELECT s.id,
                   s.name
              FROM section AS s
                   JOIN section_test AS st
                     ON s.id = pt.section_id
             WHERE st.test_id = ?;
            """;

    return null;
  }
}
