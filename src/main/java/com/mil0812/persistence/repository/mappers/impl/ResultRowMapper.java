package com.mil0812.persistence.repository.mappers.impl;

import com.mil0812.persistence.entity.impl.Result;
import com.mil0812.persistence.repository.mappers.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class ResultRowMapper implements RowMapper<Result> {
  private final UserProxy userProxy;
  private final TestProxy testProxy;
  private final SectionProxy sectionProxy;


  @Override
  public Result mapRow(ResultSet rs) throws SQLException {
    UUID id = UUID.fromString(rs.getString("id"));
    UUID userId = UUID.fromString(rs.getString("user_id"));
    UUID testId = UUID.fromString(rs.getString("test_id"));
    UUID sectionId = UUID.fromString(rs.getString("section_id"));

    return new Result(
        id,
        userProxy,
        userId,
        testProxy,
        testId,
        sectionProxy,
        sectionId,
        rs.getTimestamp("date_of_text").toLocalDateTime(),
        rs.getDouble("grade")
    );
  }
}
