package com.mil0812.presentation.controllers;

import com.mil0812.domain.service.impl.SignInService;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SignInController {

  @FXML
  private TextField loginTextField;

  @FXML
  private PasswordField paswordTextField;

  @FXML
  private Button signInButton;
  @FXML
  private Label errorMessageLabel;
  private SignInService signInService;

  @FXML
  void goBack(MouseEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main.fxml"));
    Parent root = loader.load();
    MainController controller = loader.getController();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  void signIn(ActionEvent event) {
    String login = loginTextField.getText();
    String password = paswordTextField.getText();

    boolean authenticated = signInService.authenticated(login, password);

    if (authenticated) {
      errorMessageLabel.setText("");
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/quiz-master.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      errorMessageLabel.setText("Неправильний логін або пароль...");
    }
  }
}
