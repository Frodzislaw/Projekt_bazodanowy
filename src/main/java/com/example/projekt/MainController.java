package com.example.projekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainController {
    @FXML
    private Label haslo_label;
    @FXML
    private Button ZalogujButton;
    @FXML
    private TextField login; // Add login TextField
    @FXML
    private PasswordField password; // Add password PasswordField

    @FXML
    protected void klientButton(ActionEvent event) throws IOException {
        loginAndOpenWindow("klient.fxml", "Klient", 1315, 890, event);
    }

    @FXML
    protected void pracownikButton(ActionEvent event) throws IOException {
        loginAndOpenWindow("pracownik.fxml", "Pracownik", 600, 500, event);

        LoggedInEmployee loggedInEmployee = UserManager.getInstance().getLoggedInEmployee();
        Integer id_pracownika = loggedInEmployee.getId_pracownika();
        String imie = loggedInEmployee.getImie();
        String nazwisko = loggedInEmployee.getNazwisko();
        Integer id_konta = loggedInEmployee.getId_konta();
        String rola = loggedInEmployee.getRola();

        System.out.println(id_pracownika + " " + imie + " " + nazwisko + " " + id_konta + " " + rola);


    }
    @FXML
    protected void zaloguj(ActionEvent event) {
        String email = login.getText(); // Get the entered email
        String enteredPassword = password.getText(); // Get the entered password

        Connection connection = DatabaseManager.getConnection();
        String sql = "SELECT id_konta, typ_konta FROM konta WHERE email = ? AND haslo = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, enteredPassword);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("id_konta");
                    String userRole = resultSet.getString("typ_konta");

                    if ("klient".equals(userRole)) {
                        LoggedInUser loggedInUser = createLoggedInUserFromClientTable(userId);
                        UserManager.getInstance().setLoggedInUser(loggedInUser);
                        klientButton(event);
                    } else if ("pracownik".equals(userRole)) {
                        LoggedInEmployee loggedInEmployee = createLoggedInEmployeeFromEmployeeTable(userId);
                        UserManager.getInstance().setLoggedInEmployee(loggedInEmployee);
                        pracownikButton(event);
                    }
                } else {
                    System.out.println("Invalid login credentials");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private LoggedInUser createLoggedInUserFromClientTable(int userId) {
        Connection connection = DatabaseManager.getConnection();
        String sql = "SELECT nazwa_firmy, adres, nip FROM klient WHERE id_konta = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("nazwa_firmy");
                    String address = resultSet.getString("adres");
                    String nip = resultSet.getString("nip");
                    return new LoggedInUser(userId, name, address, nip);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private LoggedInEmployee createLoggedInEmployeeFromEmployeeTable(int userId) {
        Connection connection = DatabaseManager.getConnection();
        String sql = "SELECT id_pracownika, imie, nazwisko, rola FROM pracownik WHERE id_konta = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int employeeId = resultSet.getInt("id_pracownika");
                    String firstName = resultSet.getString("imie");
                    String lastName = resultSet.getString("nazwisko");
                    String role = resultSet.getString("rola");
                    return new LoggedInEmployee(employeeId, firstName, lastName, userId, role);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



    private void loginAndOpenWindow(String fxmlFile, String title, double width, double height, ActionEvent event) throws IOException {
        // utwórz i pokaż nowe okno
        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFile));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, width, height);
        newStage.setTitle(title);
        newStage.setScene(scene);
        newStage.centerOnScreen();
        newStage.show();

        // zamknij obecne okno
        Node source = (Node) event.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
        currentStage.close();
    }
}