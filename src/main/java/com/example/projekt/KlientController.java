package com.example.projekt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class KlientController {

    @FXML
    private TableView<produkt> table;

    @FXML
    private TableColumn<produkt, Integer> colIdProduktu;

    @FXML
    private TableColumn<produkt, String> colNazwa;

    @FXML
    private TableColumn<produkt, Double> colCena;

    @FXML
    private TableColumn<produkt, String> colKategoria;

    @FXML
    private TableColumn<produkt, Integer> colIloscNaStanie;

    @FXML
    private TableColumn<produkt, String> colProducent;


    @FXML
    private Button logout_button;

    @FXML
    private BorderPane rootPane;

    @FXML
    private HBox hBoxOne;

    @FXML
    private HBox hBoxTwo;

    @FXML
    private HBox hBoxTree;

    @FXML
    private Pane centerPane;

    private Pane originalPane;

    @FXML
    public void initialize() {
        originalPane = centerPane; // zapisanie oryginalnej sceny w momencie inicjalizacji

        produktDAO dao = new produktDAO();
        ObservableList<produkt> produkty = FXCollections.observableArrayList(dao.pobierzWszystkieProdukty());

        colIdProduktu.setCellValueFactory(new PropertyValueFactory<>("id_produktu"));
        colNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        colCena.setCellValueFactory(new PropertyValueFactory<>("cena"));
        colKategoria.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
        colIloscNaStanie.setCellValueFactory(new PropertyValueFactory<>("ilosc_na_stanie"));
        colProducent.setCellValueFactory(new PropertyValueFactory<>("producent"));


        table.setItems(produkty);
    }

    @FXML
    protected void handleHBoxClick(MouseEvent event) {
        try {
            Pane pane;

            if (event.getSource() == hBoxOne) {
                pane = FXMLLoader.load(getClass().getResource("koszyk.fxml")); // Załadowanie nowego panelu
                rootPane.setCenter(pane);
            } else if (event.getSource() == hBoxTwo) {
                pane = FXMLLoader.load(getClass().getResource("hisotria_klienta.fxml")); // Załadowanie nowego panelu
                rootPane.setCenter(pane);
            } else if (event.getSource() == hBoxTree){
                rootPane.setCenter(originalPane); // Wróć do oryginalnej sceny
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void logOutAction(ActionEvent event) {
        try {
            Stage currentStage = (Stage) rootPane.getScene().getWindow();
            Stage stage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource("Logowanie.fxml"));
            Scene scene = new Scene(root, 393, 468);
            stage.setScene(scene);

            currentStage.close();
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    protected void selected(MouseEvent event){

        if (event.getSource() == hBoxOne) {
            hBoxOne.getStyleClass().add("selected");

            hBoxTwo.getStyleClass().remove("selected");
            hBoxTree.getStyleClass().remove("selected");
            event.consume();

        } else if (event.getSource() == hBoxTwo) {
            hBoxTwo.getStyleClass().add("selected");

            hBoxOne.getStyleClass().remove("selected");
            hBoxTree.getStyleClass().remove("selected");
            event.consume();

        } else if (event.getSource() == hBoxTree){
            hBoxTree.getStyleClass().add("selected");

            hBoxTwo.getStyleClass().remove("selected");
            hBoxOne.getStyleClass().remove("selected");
            event.consume();

        }
    }

}
