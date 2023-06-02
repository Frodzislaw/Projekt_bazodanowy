package com.example.projekt;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Label haslo_label;

    @FXML
    protected void klientButton() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("klient.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("Klient");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void pracownikButton() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("pracownik.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("Pracownik");
        stage.setScene(scene);
        stage.show();
    }
}