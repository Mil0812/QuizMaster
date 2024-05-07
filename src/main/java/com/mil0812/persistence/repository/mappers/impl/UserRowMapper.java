/*
package com.mil0812.persistence.repository.mappers.impl;

import com.mil0812.persistence.entity.impl.User;
import com.mil0812.persistence.repository.mappers.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UserRowMapper implements RowMapper<User> {
  public User mapRow(ResultSet rs) throws SQLException{
    return new User(
        UUID.fromString(rs.getString("id")),
        rs.getString("username"),
        rs.getString("password"),
        User.Status.valueOf(rs.getString("status"))
    );
  }
}*/
