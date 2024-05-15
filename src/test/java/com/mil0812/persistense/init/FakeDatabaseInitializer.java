package com.mil0812.persistense.init;

import com.mil0812.persistence.connection.ConnectionManager;
import com.mil0812.persistence.connection.DatabaseInitializer;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FakeDatabaseInitializer {

  public static void run(Connection connection) {
    try {
      Statement statement = connection.createStatement();
      statement.execute(getSQL("ddl.sql"));
      statement.executeUpdate(getSQL("dml.sql"));
    } catch (SQLException throwables) {
      throw new RuntimeException(throwables);
    }
  }

  private static String getSQL(final String resourceName) {
    return new BufferedReader(new InputStreamReader(Objects.requireNonNull(
        ConnectionManager.class.getClassLoader().getResourceAsStream(resourceName))))
        .lines()
        .collect(Collectors.joining("\n"));
  }

  private FakeDatabaseInitializer() {
  }
}
