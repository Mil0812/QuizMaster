package com.mil0812.persistence.repository.mappers.impl;

import com.mil0812.persistence.entity.impl.Test;
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
    UUID userId = UUID.fromString(rs.getString("user_id"));
    UUID testTypeId = UUID.fromString(rs.getString("test_type_id"));
    return new Test(
        id,
        userProxy,
        userId,
        testTypeId,
        rs.getString("title"),
        rs.getBytes("image"),
        rs.getInt("question_count"),
        sections
    );
  }
}

/*

 // TODO: make lazy loading for User and Post

@Component
public class PostRowMapper implements RowMapper<Post> {

  private final Tags tags;
  private final UserProxy userProxy;

  public PostRowMapper(Tags tags, UserProxy userProxy) {
    this.tags = tags;
    this.userProxy = userProxy;
  }

  @Override
  public Post mapRow(ResultSet rs) throws SQLException {
    UUID id = UUID.fromString(rs.getString("id"));
    UUID userId = UUID.fromString(rs.getString("user_id"));
    return new Post(
        id,
        rs.getString("slug"),
        rs.getString("title"),
        rs.getString("description"),
        rs.getString("body"),
        rs.getBytes("image"),
        rs.getBoolean("is_published"),
        rs.getTimestamp("created_at").toLocalDateTime(),
        rs.getTimestamp("updated_at").toLocalDateTime(),
        userId,
        userProxy,
        tags);
  }
}*/