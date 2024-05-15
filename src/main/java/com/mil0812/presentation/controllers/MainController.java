package com.mil0812.presentation.controllers;


import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

  @FXML
  private void goToSignInPage(ActionEvent actionEvent) throws IOException {
    // Завантаження FXML файлу нової сторінки
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/sign-in.fxml"));
    Parent root = loader.load();

    // Отримання контролера нової сторінки
    SignInController controller = loader.getController();

    // Отримання сцени з кореневим вузлом нової сторінки
    Scene scene = new Scene(root);

    // Отримання об'єкта Stage
    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

    // Встановлення нової сцени
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void goToSignUpPage(ActionEvent actionEvent) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/sign-up.fxml"));
    Parent root = loader.load();
    SignUpController controller = loader.getController();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
  }

  public void main() {
  }
}