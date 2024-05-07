package com.mil0812.persistence.connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Клас, що відповідає за підключення до бази даних
 */
public class ConnectionManager {

  private static final Logger logger = LoggerFactory.getLogger(ConnectionManager.class);


  public static void connect() {
    try (Connection connection = SQLiteConnector.getConnection();
        Statement statementForDDL = connection.createStatement();
        Statement statementForDML = connection.createStatement()) {
      statementForDDL.execute(getSQL("ddl.sql"));
      statementForDML.execute(getSQL("dml.sql"));
    } catch (SQLException e) {
      logger.info("Помилка з підключенням до SQL-файликів...");
      throw new RuntimeException(e);
    }
  }

  private static String getSQL(final String resourceName) {
    return new BufferedReader(
        new InputStreamReader(
            Objects.requireNonNull(
                SQLiteConnector.class
                    .getClassLoader()
                    .getResourceAsStream(resourceName)))).lines()
        .collect(Collectors.joining("\n"));
  }
}