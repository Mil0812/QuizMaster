package com.mil0812.presentation;

import com.mil0812.persistence.connection.ConnectionManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Start extends Application {

  ConnectionManager connectionManager;

  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main.fxml"));
    Parent root = loader.load();
    stage.setTitle("QuizMaster");
    stage.setScene(new Scene(root, 800, 600));
    stage.setResizable(false);
    stage.setMaxHeight(600);
    stage.setMinHeight(600);
    stage.setMaxWidth(800);
    stage.setMinWidth(800);
    stage.show();
  }

  @Override
  public void stop() throws Exception {
    super.stop();
    if (connectionManager != null) {
      connectionManager.closePool();
    }
  }
}
