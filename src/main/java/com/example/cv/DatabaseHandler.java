package com.example.cv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {

    private static final String DB_URL = "jdbc:sqlite:mydb.db";

    public void initializeDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS cv_data (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "full_name TEXT NOT NULL," +
                "email TEXT," +
                "phone TEXT," +
                "address TEXT," +
                "education TEXT," +
                "skills TEXT," +
                "experience TEXT," +
                "projects TEXT" +
                ");";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Database initialized.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean saveCV(String fullName, String email, String phone, String address,
                          String education, String skills, String experience, String projects) {

        String sql = "INSERT INTO cv_data(full_name, email, phone, address, education, skills, experience, projects) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, fullName);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setString(4, address);
            pstmt.setString(5, education);
            pstmt.setString(6, skills);
            pstmt.setString(7, experience);
            pstmt.setString(8, projects);

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error saving CV: " + e.getMessage());
            return false;
        }
    }
}