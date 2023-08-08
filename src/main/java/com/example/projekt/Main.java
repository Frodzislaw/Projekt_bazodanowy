package com.example.projekt;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.List;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Logowanie.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 393, 468);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        produktDAO dao = new produktDAO();
//        ObservableList<produkt> produkty = FXCollections.observableArrayList(dao.pobierzWszystkieProdukty());
//        System.out.println(produkty.get(0).getIlosc_na_stanie());

    }
}
