package com.mil0812.persistence.repository.mappers.impl;

import com.mil0812.persistence.entity.impl.TestType;
import com.mil0812.persistence.repository.mappers.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class TestTypeRowMapper implements RowMapper<TestType> {

  @Override
  public TestType mapRow(ResultSet rs) throws SQLException {
    UUID id = UUID.fromString(rs.getString("id"));

    return new TestType(
        id,
        rs.getString("name"),
        rs.getString("description"),
        rs.getString("title"),
        rs.getString("image"),
        rs.getInt("max_answer_count"),
        rs.getInt("correct_answer_count")
    );
  }
}
