package com.mil0812.persistence.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Клас, де використовується JDBC-драйвер для підключення до
 * бази даних SQLite за допомогою URL
 */
public class SQLiteConnector {
  private static final String DB_URL = "jdbc:sqlite:C:/Users/admin/IdeaProjects/QuizMaster/quiz_master.sqlite";

  static Logger logger = Logger.getLogger(SQLiteConnector.class.getName());
    public static Connection getConnection() {
      try {
        return DriverManager.getConnection(DB_URL);
      } catch ( SQLException e) {
        logger.info("Помилка з підключенням до бази даних...");
        e.printStackTrace();
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