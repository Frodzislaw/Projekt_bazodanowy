package com.example.projekt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    private Label informacja_zwrotna;


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
    public void zatwierdz() {
        LoggedInUser loggedInUser = UserManager.getInstance().getLoggedInUser();
        int userId = loggedInUser.getId();
        Integer userNip = Integer.valueOf(loggedInUser.getnip());

        double totalCena = 0.0;

        boolean quantitiesValid = true;
        for (produkt p : table.getItems()) {
            int selectedQuantity = (int) p.getIlosc_produktow().getValue();
            int availableQuantity = p.getIlosc_na_stanie();

            if (selectedQuantity > availableQuantity) {
                quantitiesValid = false;
                break;
            }

            totalCena += p.getCena() * selectedQuantity;
        }

        if (!quantitiesValid) {
            informacja_zwrotna.setText("Niepoprawne ilości produktów.");
            return;
        }

        Connection connection = DatabaseManager.getConnection();

        int newOrderId = getNewOrderId(connection);

        try {
            String insertOrderQuery = "INSERT INTO zamowienia (id_zamowienia, nip, id_pracownika, data_zamowienia, stan_zamownienia, cena_zamowienia) " +
                    "VALUES (?, ?, ?, CURRENT_DATE, 'w trakcie', ?)";

            PreparedStatement orderStatement = connection.prepareStatement(insertOrderQuery);
            orderStatement.setInt(1, newOrderId);
            orderStatement.setInt(2, userNip);
            orderStatement.setInt(3, 1); // Assuming the ID of the employee is 1
            orderStatement.setDouble(4, totalCena);

            orderStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            informacja_zwrotna.setText("Błąd podczas tworzenia zamówienia.");
            return;
        }

        try {
            String insertItemsQuery = "INSERT INTO zawartosc_zamowienia (id_zamowienia, id_produktu, ilosc, nazwa, cena) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement itemsStatement = connection.prepareStatement(insertItemsQuery);
            for (produkt p : table.getItems()) {
                int selectedQuantity = (int) p.getIlosc_produktow().getValue();
                if (selectedQuantity > 0) {
                    itemsStatement.setInt(1, newOrderId);
                    itemsStatement.setInt(2, p.getId_produktu());
                    itemsStatement.setInt(3, selectedQuantity);
                    itemsStatement.setString(4, p.getNazwa());
                    itemsStatement.setDouble(5, p.getCena());

                    itemsStatement.executeUpdate();

                    String updateProductQuery = "UPDATE produkt SET ilosc_na_stanie = ilosc_na_stanie - ? WHERE id_produktu = ?";
                    PreparedStatement updateProductStatement = connection.prepareStatement(updateProductQuery);
                    updateProductStatement.setInt(1, selectedQuantity);
                    updateProductStatement.setInt(2, p.getId_produktu());
                    updateProductStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            informacja_zwrotna.setText("Błąd podczas dodawania produktów do zamówienia.");
            return;
        }

        informacja_zwrotna.setText("Zamówienie zatwierdzone. Cena: " + totalCena);
    }
    public int getNewOrderId(Connection connection) {
        int newOrderId = 1; // Default value

        try {
            String query = "SELECT MAX(id_zamowienia) FROM zamowienia";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                newOrderId = resultSet.getInt(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newOrderId;
    }
    @FXML
    public void anuluj(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        Scene scene = sourceButton.getScene();
        Stage stage = (Stage) scene.getWindow();

        stage.close();
    }
}
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
