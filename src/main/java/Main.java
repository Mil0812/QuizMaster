import com.mil0812.persistence.ApplicationConfig;
import com.mil0812.persistence.connection.ConnectionManager;
import com.mil0812.persistence.connection.DatabaseInitializer;
import com.mil0812.persistence.entity.impl.Section;
import com.mil0812.persistence.entity.impl.Test;
import com.mil0812.persistence.entity.impl.User;
import com.mil0812.persistence.entity.impl.User.Status;
import com.mil0812.persistence.entity.proxy.impl.UserProxyImpl;
import com.mil0812.persistence.entity.proxy.interfaces.Sections;
import com.mil0812.persistence.entity.proxy.interfaces.UserProxy;
import com.mil0812.persistence.repository.interfaces.UserRepository;
import com.mil0812.persistence.unitOfWork.PersistenceContext;
import java.util.Set;
import java.util.UUID;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static AnnotationConfigApplicationContext persistenceContext;
  static final Logger logger = LoggerFactory.getLogger(Main.class);


  public static void main(String[] args) {
    persistenceContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    var connectionManager = persistenceContext.getBean(ConnectionManager.class);
    var databaseInitializer = persistenceContext.getBean(DatabaseInitializer.class);

    try {
      databaseInitializer.init();

    } finally {
      connectionManager.closePool();
    }

    //test(databaseInitializer, connectionManager);
  }


  public static void test(DatabaseInitializer databaseInitializer,
      ConnectionManager connectionManager) {

    try {
      databaseInitializer.init();
      var persistenceContext = Main.persistenceContext.getBean(PersistenceContext.class);

     /* logger.info("Registering NEW");
      persistenceContext.users.registerNew(
          new User(
              null,
              "test55",
              "9876",
              "Test1",
              "Yeah1",
              "gjhgjhgn@gmail.com",
              Status.TEACHER
          ));

      //безпосередньо додавання
      persistenceContext.users.commit();

      logger.info(STR."FOUND USER: \{persistenceContext.users.repository.findByLogin("test55")}");*/
/*
      logger.info("Registering MODIFIED");
      persistenceContext.users.registerModified(
          new User(
              UUID.fromString("ce66389c-ab6e-42d8-aa79-b6b9abf41cdb"),
              "lollipop321",
              "lily098",
              "Фанні",
              "Марвен",
              "lollipop111@i.ua",
              Status.STUDENT
          ));

      persistenceContext.users.commit();

      logger.info(
          STR."MODIFIES USER: \{persistenceContext.users.repository
              .findById(UUID.fromString("ce66389c-ab6e-42d8-aa79-b6b9abf41cdb"))}");

      logger.info("Registering DELETED");
      persistenceContext.users.registerDeleted(
          UUID.fromString("092e6db3-e2e1-4e7b-9a07-2bb6d70ad5c0"));
      persistenceContext.users.commit();

      logger.info(
          STR."USER IS DELETED");*/

     /* persistenceContext.tests.registerNew(
          new Test(
              null,
              null,
              UUID.fromString("943c22dc-63c8-48ed-be61-79ee6c20867a"),
              UUID.fromString("d4e83df7-0b97-45df-a2f1-f85eba2fcf5f"),
              UUID.fromString("5e8e2e90-b5cb-456b-a006-73c8c24542eb"),
              "Класний тест",
              "",
              5,
              null
          )
      );
      persistenceContext.tests.commit();
      logger.info(STR."CREATED TEST: \{persistenceContext.tests.testRepository
          .findByAuthor(UUID.fromString("943c22dc-63c8-48ed-be61-79ee6c20867a"))}");*/

    } finally {
      connectionManager.closePool();
    }
  }
}