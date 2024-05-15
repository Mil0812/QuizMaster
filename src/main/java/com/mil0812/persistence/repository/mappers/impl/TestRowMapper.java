package com.mil0812.persistence.repository.mappers.impl;

import com.mil0812.persistence.entity.impl.Test;
import com.mil0812.persistence.entity.proxy.interfaces.Sections;
import com.mil0812.persistence.entity.proxy.interfaces.UserProxy;
import com.mil0812.persistence.repository.mappers.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class TestRowMapper implements RowMapper<Test> {

  private final Sections sections;
  private final UserProxy userProxy;

  public TestRowMapper(Sections sections, UserProxy userProxy) {
    this.sections = sections;
    this.userProxy = userProxy;
  }

  @Override
  public Test mapRow(ResultSet rs) throws SQLException {
    UUID id = UUID.fromString(rs.getString("id"));
    UUID userId = UUID.fromString(rs.getString("author_id"));
    UUID testTypeId = UUID.fromString(rs.getString("type_id"));
    UUID sectionId = UUID.fromString(rs.getString("section_id"));
    return new Test(
        id,
        userProxy,
        userId,
        testTypeId,
        sectionId,
        rs.getString("title"),
        rs.getString("image"),
        rs.getInt("question_count"),
        sections
    );
  }
}