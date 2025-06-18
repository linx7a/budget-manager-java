package com.alina.budgetmanager.db;

import com.alina.budgetmanager.domain.Transaction;

import java.sql.*;
import java.time.LocalDateTime;

public class DatabaseManager {

    private static final String URL = "jdbc:postgresql://localhost:5432/budgetdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "твой_пароль";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void insertTransaction(Transaction t) {
        String sql = "INSERT INTO transactions (amount, type, datetime) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, t.getAmount());
            stmt.setString(2, t.getType().toString());
            stmt.setTimestamp(3, Timestamp.valueOf(t.getDateTime()));
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ошибка при сохранении в БД: " + e.getMessage());
        }
    }

    public static void printAllTransactions() {
        String sql = "SELECT amount, type, datetime FROM transactions ORDER BY datetime";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                double amount = rs.getDouble("amount");
                String type = rs.getString("type");
                LocalDateTime dateTime = rs.getTimestamp("datetime").toLocalDateTime();

                System.out.printf("%s: %.2f (%s)%n", type, amount, dateTime);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при чтении из БД: " + e.getMessage());
        }
    }
}
