package com.gokart.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gokart.config.Dbconfig;
import com.gokart.model.UserModel;
import com.gokart.util.PasswordUtil;

public class RegisterService {

    // Check if username already exists
    public boolean usernameExists(String username) {
        String query = "SELECT COUNT(*) FROM users WHERE username = ?";
        try (Connection conn = Dbconfig.getDbConnection()) {
            if (conn == null) return false;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, username);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1) > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Check if email already exists
    public boolean emailExists(String email) {
        String query = "SELECT COUNT(*) FROM users WHERE email = ?";
        try (Connection conn = Dbconfig.getDbConnection()) {
            if (conn == null) return false;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, email);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1) > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Check if phone number already exists
    public boolean phoneNumberExists(String phoneNumber) {
        String query = "SELECT COUNT(*) FROM users WHERE phoneNumber = ?";
        try (Connection conn = Dbconfig.getDbConnection()) {
            if (conn == null) return false;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, phoneNumber);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1) > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Register a new user with encrypted password
    public boolean register(UserModel user) {
        String query = "INSERT INTO users (firstName, lastName, username, birthday, gender, email, phoneNumber, password, role) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Dbconfig.getDbConnection()) {
            if (conn == null) return false;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {

                // Generate salt and hash password
                byte[] salt = PasswordUtil.generateSalt();
                String hashedPassword = PasswordUtil.hashPassword(user.getPassword(), salt);

                pstmt.setString(1, user.getFirstName());
                pstmt.setString(2, user.getLastName());
                pstmt.setString(3, user.getUsername());
                pstmt.setString(4, user.getBirthday());
                pstmt.setString(5, user.getGender());
                pstmt.setString(6, user.getEmail());
                pstmt.setString(7, user.getPhoneNumber());
                pstmt.setString(8, hashedPassword); // Store encrypted password
                pstmt.setString(9, user.getRole());

                int rowsAffected = pstmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
