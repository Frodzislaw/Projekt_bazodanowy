package com.example.projekt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ZamowienieController {

    @FXML
    private TableView<produkt> table;


    @FXML
    private TableColumn<produkt, String> colNazwa;

    @FXML
    private TableColumn<produkt, Double> colCena;

    @FXML
    private TableColumn<produkt, String> colKategoria;

    @FXML
    private TableColumn<produkt, Integer> colIloscNaStanie;
    @FXML
    private TableColumn<produkt, Integer> colComboBox;


    @FXML
    public void initialize() {

        produktDAO dao = new produktDAO();
        ObservableList<produkt> produkty = FXCollections.observableArrayList(dao.pobierzWszystkieProdukty());

        colNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        colCena.setCellValueFactory(new PropertyValueFactory<>("cena"));
        colKategoria.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
        colIloscNaStanie.setCellValueFactory(new PropertyValueFactory<>("ilosc_na_stanie"));
        colComboBox.setCellValueFactory(new PropertyValueFactory<>("ilosc_produktow"));

        table.setItems(produkty);
    }
    @FXML
    public void zatwierdz(){

        ////////////////////////////////////////////////
        LoggedInUser loggedInUser = UserManager.getInstance().getLoggedInUser();
        int userId = loggedInUser.getId();
        String userEmail = loggedInUser.getEmail();
        String userRole = loggedInUser.getRole();
        String userNip = loggedInUser.getnip(); // TO
        System.out.println(userId + " " + userEmail + " " + userRole + " " + userNip);
        Integer ilosc = (Integer) table.getItems().get(0).getIlosc_produktow().getValue();
        //////////////////////////////////////////////////////////////////
        double cena = table.getItems().get(0).getCena() * ilosc;
        System.out.println(cena);
        ///////////////////////////////////////////////

        //System.out.println(userNip);
//        String stan_zamowienia = "w trakcie";
//        Integer id_pracowika = 1;
//        String data = "2023-08-06";//taki format tylko curent date
//        System.out.println("TUTAJ JEST CENA : "+ cena);
//        System.out.println( table.getItems().get(0).getId_produktu());
////
//        System.out.println(table.getItems().get(0).getIlosc_produktow().getValue()); // obecnie wybrana ilosc
//        System.out.println(table.getItems().get(0).getIlosc_na_stanie()); //maksymalna ilosc
//
//       System.out.println(table.getItems().get(1).getIlosc_produktow().getValue());
//       System.out.println(table.getItems().get(2).getIlosc_produktow().getValue());
//       System.out.println(table.getItems().get(3).getIlosc_produktow().getValue());
//       System.out.println(table.getItems().get(4).getIlosc_produktow().getValue());

    }
    @FXML
    public void anuluj(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        Scene scene = sourceButton.getScene();
        Stage stage = (Stage) scene.getWindow();

        stage.close();
    }
}

