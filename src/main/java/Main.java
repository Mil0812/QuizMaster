import com.mil0812.persistence.connection.ConnectionManager;
import com.mil0812.persistence.connection.SQLiteConnector;
import java.sql.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

  private static final Logger logger = LoggerFactory.getLogger(Main.class);


  public static void main(String[] args) {
    Connection connection = null;

    try {
      try {
        connection = SQLiteConnector.getConnection();
        logger.info("Connected successfully!");
      } catch (Exception e) {
        logger.info("Шото пошло не так...");

        throw new RuntimeException(e);
      }
      ConnectionManager.connect();
    } finally {
      SQLiteConnector.close(connection);
    }
  }
}