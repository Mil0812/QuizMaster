package com.mil0812.persistence.repository.impl;

import com.mil0812.persistence.connection.ConnectionManager;
import com.mil0812.persistence.entity.impl.Section;
import com.mil0812.persistence.entity.impl.Test;
import com.mil0812.persistence.repository.GenericJdbcRepository;
import com.mil0812.persistence.repository.interfaces.TableTitles;
import com.mil0812.persistence.repository.interfaces.TestRepository;
import com.mil0812.persistence.repository.mappers.impl.SectionRowMapper;
import com.mil0812.persistence.repository.mappers.impl.TestRowMapper;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepositoryImpl extends GenericJdbcRepository<Test>
    implements TestRepository {

  private final SectionRowMapper sectionRowMapper;
  private final ManyToManyJBDC<Section> manyToManyJBDC;

  public TestRepositoryImpl(ConnectionManager connectionManager,
      TestRowMapper testRowMapper, SectionRowMapper sectionRowMapper,
      ManyToManyJBDC<Section> manyToManyJBDC) {
    super(connectionManager, testRowMapper, TableTitles.TEST.getName());
    this.sectionRowMapper = sectionRowMapper;
    this.manyToManyJBDC = manyToManyJBDC;
  }


  @Override
  protected Map<String, Object> tableValues(Test test) {
    Map<String, Object> values = new LinkedHashMap<>();

    if (Objects.nonNull(test.userId())) {
      values.put("author_id", test.userId());
    }
    if (Objects.nonNull(test.testTypeId())) {
      values.put("type_id", test.testTypeId());
    }
    if (Objects.nonNull(test.sectionId())) {
      values.put("section_id", test.sectionId());
    }
    if (!test.title().isBlank()) {
      values.put("title", test.title());
    }
    if (!test.image().isEmpty()) {
      values.put("image", test.image());
    }
    values.put("question_count", test.questionCount());

    return values;
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
    return findBy("author_id", userId);
  }

  @Override
  public Optional<Test> findByTestType(UUID testTypeId) {
    return findBy("type_id", testTypeId);
  }

  @Override
  public Set<Test> findAllByAuthorId(UUID userId) {
    return findAllWhere(STR."author_id = \{userId}");
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

    return manyToManyJBDC.getEntities(
        testId, sql, sectionRowMapper,
        STR."Помилка при отриманні всіх розділів тесту по id: \{testId}"
    );
  }
}
