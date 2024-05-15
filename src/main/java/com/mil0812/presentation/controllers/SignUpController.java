package com.mil0812.presentation.controllers;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SignUpController {

  @FXML
  private TextField emailTextField;

  @FXML
  private TextField firstNameTextField;

  @FXML
  private TextField lastNameTextField;

  @FXML
  private TextField loginTextField;

  @FXML
  private PasswordField passwordTextField;

  @FXML
  private ComboBox<String> roleComboBox;

  @FXML
  private Button signUpButton;
  String selectedRole;

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
  void signUp(ActionEvent event) {
    selectedRole = roleComboBox.getValue();
  }

  public void initialize() {
    // Ініціалізуємо ComboBox
    roleComboBox.setItems(FXCollections.observableArrayList("student", "teacher"));
  }

}
