package com.example.projekt.baza;

import com.example.projekt.DatabaseManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class produktDAO {
    public List<produkt> pobierzWszystkieProdukty() {
        List<produkt> listaProduktow = new ArrayList<>();

        // Pobierz połączenie z DatabaseManager
        Connection connection = DatabaseManager.getConnection();

        // Utwórz zapytanie SQL do pobrania wszystkich produktów
        String sql = "SELECT * FROM produkt";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            // Przejrzyj wszystkie wyniki i utwórz obiekt produkt dla każdego z nich
            while (resultSet.next()) {
                produkt produkt = new produkt(
                        resultSet.getInt("id_produktu"),
                        resultSet.getString("nazwa"),
                        resultSet.getDouble("cena"),
                        resultSet.getString("kategoria"),
                        resultSet.getInt("ilosc_na_stanie"),
                        resultSet.getString("producent")
                );

                listaProduktow.add(produkt);
            }
        } catch (SQLException e) {
            // obsłuż wyjątek
            e.printStackTrace();
        }

        return listaProduktow;
    }
}
