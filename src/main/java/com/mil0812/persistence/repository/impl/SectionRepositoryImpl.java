package com.mil0812.persistence.repository.impl;

import com.mil0812.persistence.connection.ConnectionManager;
import com.mil0812.persistence.entity.impl.Section;
import com.mil0812.persistence.entity.impl.Test;
import com.mil0812.persistence.repository.GenericJdbcRepository;
import com.mil0812.persistence.repository.interfaces.SectionRepository;
import com.mil0812.persistence.repository.interfaces.TableTitles;
import com.mil0812.persistence.repository.mappers.RowMapper;
import com.mil0812.persistence.repository.mappers.impl.SectionRowMapper;
import com.mil0812.persistence.repository.mappers.impl.TestRowMapper;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class SectionRepositoryImpl extends GenericJdbcRepository<Section>
    implements SectionRepository {

  private final TestRowMapper testRowMapper;
  private final ManyToManyJBDC<Test> manyToManyJBDC;

  public SectionRepositoryImpl
      (ConnectionManager connectionManager,
          RowMapper<Section> rowMapper,
          TestRowMapper testRowMapper,
          ManyToManyJBDC<Test> manyToManyJBDC) {
    super(connectionManager, rowMapper, TableTitles.SECTION.getName());
    this.testRowMapper = testRowMapper;
    this.manyToManyJBDC = manyToManyJBDC;
  }


  @Override
  public boolean attach(UUID sectionId, UUID testId) {
    final String sql =
        """
            INSERT INTO section_test(section_id, test_id)
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
  public Set<Test> getTests(UUID sectionId) {
    final String sql =
        """
            SELECT t.id,
                   t.user_id,
                   t.type_id,
                   t.title,
                   t.question_count
              FROM tests AS t
                   JOIN section_test AS st
                     ON t.id = st.test_id
             WHERE st.section_id = ?;
            """;
    return manyToManyJBDC.getEntities(
        sectionId,
        sql,
        testRowMapper,
        STR."Помилка при отриманні всіх тестів розділу за id: \{sectionId}");
  }

  @Override
  protected Map<String, Object> tableValues(Section section) {
    Map<String, Object> values = new LinkedHashMap<>();
    if (!section.name().isBlank()) {
      values.put("name", section.name());
    }
    return values;
  }
}
