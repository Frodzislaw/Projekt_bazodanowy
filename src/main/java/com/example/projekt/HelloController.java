package com.example.projekt;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label haslo_label;

    @FXML
    protected void klient() {
        haslo_label.setText("2 BUŁKI!");
    }
}