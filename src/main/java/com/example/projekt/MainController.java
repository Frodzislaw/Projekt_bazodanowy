package com.example.projekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Label haslo_label;

    @FXML
    protected void klientButton(ActionEvent event) throws IOException {
        // utwórz i pokaż nowe okno
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("klient.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1315, 890);
        newStage.setTitle("Klient");
        newStage.setScene(scene);
        newStage.centerOnScreen();
        newStage.show();

        // zamknij obecne okno
        Node source = (Node) event.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    protected void pracownikButton() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("pracownik.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 500);
        stage.setTitle("Pracownik");
        stage.setScene(scene);
        stage.show();
    }
}