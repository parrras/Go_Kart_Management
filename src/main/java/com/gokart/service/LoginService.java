package com.gokart.service;

import com.gokart.model.UserModel;
import com.gokart.util.PasswordUtil;

public class LoginService {

    private UserService userService;

    public LoginService() {
        this.userService = new UserService();
    }

    /**
     * Validates user credentials.
     *
     * @param username The username provided by the user.
     * @param password The plain text password provided by the user.
     * @return UserModel object if credentials are valid, null otherwise.
     */
    public UserModel validateUser(String username, String password) {
        if (username == null || username.trim().isEmpty() || password == null || password.isEmpty()) {
            return null;
        }

        try {
            UserModel user = userService.getUserByUsername(username);

            if (user != null) {
                // Securely verify password using PasswordUtil
                if (PasswordUtil.verifyPassword(password, user.getPassword())) {
                    return user;
                }
            }
        } catch (Exception e) {
            System.err.println("Error during user validation: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}
