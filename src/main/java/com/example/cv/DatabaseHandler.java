package com.example.cv;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection(DB_URL);
                 Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // CREATE
    public boolean saveCV(String fullName, String email, String phone, String address,
                          String education, String skills, String experience, String projects) {
        String sql = "INSERT INTO cv_data(full_name, email, phone, address, education, skills, experience, projects) VALUES(?,?,?,?,?,?,?,?)";
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
            e.printStackTrace();
            return false;
        }
    }

    // READ (Fetch All)
    public List<CV> getAllCVs() {
        List<CV> cvList = new ArrayList<>();
        String sql = "SELECT * FROM cv_data";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                cvList.add(new CV(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("education"),
                        rs.getString("skills"),
                        rs.getString("experience"),
                        rs.getString("projects")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cvList;
    }

    // UPDATE
    public boolean updateCV(int id, String fullName, String email, String phone, String address,
                            String education, String skills, String experience, String projects) {
        String sql = "UPDATE cv_data SET full_name=?, email=?, phone=?, address=?, education=?, skills=?, experience=?, projects=? WHERE id=?";
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
            pstmt.setInt(9, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean deleteCV(int id) {
        String sql = "DELETE FROM cv_data WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}