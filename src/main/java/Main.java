import com.mil0812.persistence.database.ConnectionManager;
import com.mil0812.persistence.database.SQLiteConnector;
import java.sql.Connection;
import java.util.logging.Logger;

public class Main {

  static Logger logger = Logger.getLogger(Main.class.getName());


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