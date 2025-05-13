package com.gokart.dao;

import com.gokart.config.Dbconfig;
import com.gokart.model.UserModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UserDoa {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public UserDoa() {
        // Default constructor
    }

    // ✅ NEW: Get user by username (used in profile view/update)
    public UserModel getUserByUsername(String username) {
        UserModel user = null;
        String sql = "SELECT userID, firstName, lastName, username, birthday, gender, email, phoneNumber, password, role FROM users WHERE username = ?";

        try (Connection connection = Dbconfig.getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new UserModel();
                    user.setUserID(resultSet.getInt("userID"));
                    user.setFirstName(resultSet.getString("firstName"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setUsername(resultSet.getString("username"));

                    Date sqlBirthday = resultSet.getDate("birthday");
                    if (sqlBirthday != null) {
                        user.setBirthday(sdf.format(sqlBirthday));
                    }

                    user.setGender(resultSet.getString("gender"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPhoneNumber(resultSet.getString("phoneNumber"));
                    user.setPassword(resultSet.getString("password")); // Required for login/profile edit
                    user.setRole(resultSet.getString("role"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<UserModel> getAllUsers() {
        List<UserModel> users = new ArrayList<>();
        String sql = "SELECT userID, firstName, lastName, username, birthday, gender, email, phoneNumber, role FROM users";

        try (Connection connection = Dbconfig.getDbConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                UserModel user = new UserModel();
                user.setUserID(rs.getInt("userID"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setUsername(rs.getString("username"));

                Date sqlBirthday = rs.getDate("birthday");
                if (sqlBirthday != null) {
                    user.setBirthday(sdf.format(sqlBirthday));
                }

                user.setGender(rs.getString("gender"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    //  Add User
    public void addUser(UserModel user) {
        String sql = "INSERT INTO users (firstName, lastName, username, birthday, gender, email, phoneNumber, password, role) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = Dbconfig.getDbConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getUsername());
            stmt.setDate(4, user.getBirthday() != null ? Date.valueOf(user.getBirthday()) : null);
            stmt.setString(5, user.getGender());
            stmt.setString(6, user.getEmail());
            stmt.setString(7, user.getPhoneNumber());
            stmt.setString(8, user.getPassword());
            stmt.setString(9, user.getRole());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update User
    public void updateUser(UserModel user) {
        String sql = "UPDATE users SET firstName=?, lastName=?, username=?, birthday=?, gender=?, email=?, phoneNumber=?, role=? WHERE userID=?";

        try (Connection connection = Dbconfig.getDbConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getUsername());
            stmt.setDate(4, user.getBirthday() != null ? Date.valueOf(user.getBirthday()) : null);
            stmt.setString(5, user.getGender());
            stmt.setString(6, user.getEmail());
            stmt.setString(7, user.getPhoneNumber());
            stmt.setString(8, user.getRole());
            stmt.setInt(9, user.getUserID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete User
    public void deleteUser(String firstName, String lastName) {
        String sql = "DELETE FROM users WHERE firstName = ? AND lastName = ?";

        try (Connection connection = Dbconfig.getDbConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     public UserModel getUserById(int userId) {
        UserModel user = null;
        String sql = "SELECT userID, firstName, lastName, username, birthday, gender, email, phoneNumber, role FROM users WHERE userID = ?";

        try (Connection connection = Dbconfig.getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new UserModel();
                    user.setUserID(resultSet.getInt("userID"));
                    user.setFirstName(resultSet.getString("firstName"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setUsername(resultSet.getString("username"));
                    Date sqlBirthday = resultSet.getDate("birthday");
                    if (sqlBirthday != null) {
                         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        user.setBirthday(sdf.format(sqlBirthday));
                    }
                    user.setGender(resultSet.getString("gender"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPhoneNumber(resultSet.getString("phoneNumber"));
                    user.setRole(resultSet.getString("role"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}