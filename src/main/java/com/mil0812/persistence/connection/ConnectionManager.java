package com.mil0812.persistence.connection;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Клас, що відповідає за підключення до бази даних
 */

@Component
public class ConnectionManager {

  private static final String URL_KEY = "db.url";
  private static final String USERNAME_KEY = "db.username";
  private static final String PASSWORD_KEY = "db.password";
  private static final String POOL_SIZE_KEY = "db.pool.size";
  private static final Integer DEFAULT_POOL_SIZE = 10;
  private final PropertyManager propertyManager;
  private BlockingQueue<Connection> pool;
  private List<Connection> sourceConnections;
  final Logger logger = LoggerFactory.getLogger(ConnectionManager.class);

  public ConnectionManager(PropertyManager propertyManager) {
    this.propertyManager = propertyManager;
    initConnectionPool();
  }

  public Connection get() {
    try {
      logger.info("Підключення отримано з пулу [%d]...".formatted(pool.size()));
      Connection connection = pool.take();
      connection.setAutoCommit(true);
      return connection;
    } catch (InterruptedException e) {
      logger.error("Помилка при отриманні підключення з пулу %s...".formatted(e));
      throw new RuntimeException(e);
    } catch (SQLException e) {
      logger.error("Не вдалося встановити автофіксацію для з'єднання з пулом %s...".formatted(e));
      throw new RuntimeException(e);
    }
  }

  public void closePool() {
    try {
      for (Connection sourceConnection : sourceConnections) {
        sourceConnection.close();
      }
      logger.info("Всі підключення успішно закриті!");
    } catch (SQLException e) {
      logger.error("Не вдалося правильно закрити підключення %s...".formatted(e));
      throw new RuntimeException(e);
    }
  }

  private Connection open() {
    try {
      return DriverManager.getConnection(
          propertyManager.get(URL_KEY),
          propertyManager.get(USERNAME_KEY),
          propertyManager.get(PASSWORD_KEY));
    } catch (SQLException e) {
      logger.error("Помилка при відкритті підключення... %s".formatted(e));
      throw new RuntimeException(e);
    }
  }

  /**
   * Метод для ініціалізації пулу підключення
   * Що робить?
   * 1) отримує розмір пулу (дефолтний або заданий у властивостях)
   * 2) створюємо чергу, яка підтримує багатопоточність
   * 3) через цикл відкриваємо всі підключення і переписуємо їх на Proxy
   * (тобто створюємо тимчасовий Proxy-клас, який реалізує інтерфейс Connection)
   */
  private void initConnectionPool() {
    String poolSize = propertyManager.get(POOL_SIZE_KEY);
    int size = poolSize == null ? DEFAULT_POOL_SIZE : Integer.parseInt(poolSize);
    logger.info("Розмір пулу підключення - %s".formatted(size));
    pool = new ArrayBlockingQueue<>(size);
    sourceConnections = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      Connection connection = open();
      Connection proxyConnection =
          (Connection)
              Proxy.newProxyInstance(
                  ConnectionManager.class.getClassLoader(),
                  new Class[] {Connection.class},
                  ((proxy, method, args) ->
                      method.getName().equals("close")
                          ? pool.add((Connection) proxy)
                          : method.invoke(connection, args)));
      pool.add(proxyConnection);
      sourceConnections.add(connection);
      logger.info("Підключення №%d відкрито!".formatted(i + 1));
    }
  }
}