package com.example.projekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static Connection connection;

    public static void openConnection() {
        String url = "jdbc:postgresql://localhost:5432/projekt";
        String user = "postgres";
        String password = "patryk06";

        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Połączono z bazą danych!");
        } catch (SQLException e) {
            System.err.println("Błąd podczas połączenia z bazą danych!");
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Połączenie z bazą danych zostało zamknięte!");
            } catch (SQLException e) {
                System.err.println("Błąd podczas zamykania połączenia z bazą danych!");
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            openConnection();
        }
        return connection;
    }
}
