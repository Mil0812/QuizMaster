package com.mil0812.persistence.repository.mappers.impl;

import com.mil0812.persistence.entity.impl.Question;
import com.mil0812.persistence.entity.proxy.impl.TestsProxy;
import com.mil0812.persistence.entity.proxy.interfaces.TestProxy;
import com.mil0812.persistence.repository.mappers.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class QuestionRowMapper implements RowMapper<Question> {
  private final TestProxy testProxy;

  public QuestionRowMapper(TestProxy testProxy) {
    this.testProxy = testProxy;
  }

  @Override
  public Question mapRow(ResultSet rs) throws SQLException {
    UUID id = UUID.fromString(rs.getString("id"));
    UUID testId = UUID.fromString(rs.getString("test_id"));

    return new Question(
        id,
        testProxy,
        testId,
        rs.getString("question_text")
    );
  }
}
