package com.gokart.service;

import com.gokart.model.UserModel;
import com.gokart.config.Dbconfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    // Method to get user by username
    public UserModel getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection connection = Dbconfig.getDbConnection()) {
            if (connection == null) return null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return mapResultSetToUser(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to check if username exists
    public boolean usernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        return checkIfExists(sql, username);
    }

    // Method to check if email exists
    public boolean emailExists(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        return checkIfExists(sql, email);
    }

    // Method to check if phone number exists
    public boolean phoneNumberExists(String phoneNumber) {
        String sql = "SELECT COUNT(*) FROM users WHERE phoneNumber = ?";
        return checkIfExists(sql, phoneNumber);
    }

    // Generic check if a value exists (username, email, phone)
    private boolean checkIfExists(String sql, String value) {
        try (Connection connection = Dbconfig.getDbConnection()) {
            if (connection == null) return false;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, value);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt(1) > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Register user (insert user into the database)
    public boolean registerUser(UserModel user) {
        String sql = "INSERT INTO users (firstName, lastName, username, birthday, gender, email, phoneNumber, password, role) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = Dbconfig.getDbConnection()) {
            if (connection == null) return false;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.setString(3, user.getUsername());
                preparedStatement.setString(4, user.getBirthday());
                preparedStatement.setString(5, user.getGender());
                preparedStatement.setString(6, user.getEmail());
                preparedStatement.setString(7, user.getPhoneNumber());
                preparedStatement.setString(8, user.getPassword()); // Ideally, hash before storing
                preparedStatement.setString(9, user.getRole());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Map ResultSet to UserModel
    private UserModel mapResultSetToUser(ResultSet resultSet) throws SQLException {
        UserModel user = new UserModel();
        user.setUserID(resultSet.getInt("userID"));
        user.setFirstName(resultSet.getString("firstName"));
        user.setLastName(resultSet.getString("lastName"));
        user.setUsername(resultSet.getString("username"));
        user.setBirthday(resultSet.getString("birthday"));
        user.setGender(resultSet.getString("gender"));
        user.setEmail(resultSet.getString("email"));
        user.setPhoneNumber(resultSet.getString("phoneNumber"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(resultSet.getString("role"));
        return user;
    }
}
