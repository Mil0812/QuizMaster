import com.mil0812.persistence.ApplicationConfig;
import com.mil0812.persistence.connection.ConnectionManager;
import com.mil0812.persistence.connection.DatabaseInitializer;
import com.mil0812.persistence.entity.impl.User;
import com.mil0812.persistence.entity.impl.User.Status;
import com.mil0812.persistence.unitOfWork.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  final Logger logger = LoggerFactory.getLogger(Main.class);
  public static AnnotationConfigApplicationContext persistenceContext;

  public static void main(String[] args) {
    var context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    var connectionManager = context.getBean(ConnectionManager.class);
    var databaseInitializer = context.getBean(DatabaseInitializer.class);

    try {
      databaseInitializer.init();

      //реєстрація на додавання
      var persistenceContext = context.getBean(PersistenceContext.class);
      persistenceContext.users.registerNew(
          new User(
              null,
              "mila08",
              "00998",
              "Mila",
              "Kulak",
              "some_email@gmail.com",
              Status.STUDENT
          ));

      //безпосередньо додавання
      persistenceContext.users.commit();

      //persistenceContext.users.repository.findByLogin("mila08");

    } finally {
      connectionManager.closePool();
    }
  }
}