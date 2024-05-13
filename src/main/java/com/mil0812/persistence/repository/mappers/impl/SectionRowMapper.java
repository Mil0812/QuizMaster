package com.mil0812.persistence.repository.mappers.impl;


import com.mil0812.persistence.entity.impl.Section;
import com.mil0812.persistence.entity.proxy.interfaces.Tests;
import com.mil0812.persistence.repository.mappers.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class SectionRowMapper  implements RowMapper<Section> {
  private final Tests tests;

  public SectionRowMapper(Tests tests) {
    this.tests = tests;
  }

  @Override
  public Section mapRow(ResultSet rs) throws SQLException {
    UUID id = UUID.fromString(rs.getString("id"));
    return new Section(
        id,
        rs.getString("name"),
        tests
    );
  }
}
