package com.mil0812.persistence.repository.impl;

import com.mil0812.persistence.connection.ConnectionManager;
import com.mil0812.persistence.exception.EntityNotFound;
import com.mil0812.persistence.exception.ErrorOnDelete;
import com.mil0812.persistence.repository.mappers.RowMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class ManyToManyJBDC<T>{
  private final ConnectionManager connectionManager;

  public ManyToManyJBDC(ConnectionManager connectionManager) {
    this.connectionManager = connectionManager;
  }

  protected Set<T> getEntities(
      UUID entityId, String sql, RowMapper<T> rowMapper, String exceptionMessage) {
    try (Connection connection = connectionManager.get();
        PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setObject(1, entityId, Types.OTHER);
      ResultSet resultSet = statement.executeQuery();

      Set<T> entities = new LinkedHashSet<>();
      while (resultSet.next()) {
        entities.add(rowMapper.mapRow(resultSet));
      }
      return entities;
    } catch (SQLException throwables) {
      throw new EntityNotFound(exceptionMessage);
    }
  }

  protected boolean executeUpdate(
      UUID firstId, UUID secondId, String sql, String exceptionMessage) {
    try (Connection connection = connectionManager.get();
        PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setObject(1, firstId, Types.OTHER);
      statement.setObject(2, secondId, Types.OTHER);
      return statement.executeUpdate() > 0;
    } catch (SQLException throwables) {
      throw new ErrorOnDelete(exceptionMessage);
    }
  }
}
