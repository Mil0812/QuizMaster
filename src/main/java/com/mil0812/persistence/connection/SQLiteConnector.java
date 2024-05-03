package com.mil0812.persistence.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Клас, де використовується JDBC-драйвер для підключення до
 * бази даних SQLite за допомогою URL
 */
public class SQLiteConnector {
  private static final String DB_URL = "jdbc:sqlite:db/quiz_master.sqlite";

  private static final Logger logger = LoggerFactory.getLogger(SQLiteConnector.class);

  public static Connection getConnection() {
    try {
      return DriverManager.getConnection(DB_URL);
    } catch ( SQLException e) {
      logger.info("Помилка з підключенням до бази даних...");
      return null;
    }
  }

  public static void close(Connection connection){
    if(connection!=null){
      try{
        connection.close();
      } catch (SQLException e) {
        logger.info("Чото якто не вдається закрити з'єднання...");
        throw new RuntimeException(e);
      }
    }
  }
}