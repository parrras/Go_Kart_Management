package com.gokart.service;

import com.gokart.model.UserModel;
// Assuming UserService exists and has a method to get user by username
// import com.GoKart.service.UserService; 

public class LoginService {

    private UserService userService; // Dependency on UserService

    public LoginService() {
        // Initialize UserService (consider dependency injection)
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
            return null; // Basic validation
        }

        try {
            // Fetch user by username using UserService
            // TODO: Implement getUserByUsername in UserService if it doesn't exist
            UserModel user = userService.getUserByUsername(username); 

            if (user != null) {
                // TODO: Implement proper password hashing and comparison (e.g., using BCrypt)
                // This is a placeholder and insecure!
                if (password.equals(user.getPassword())) { 
                    return user; // Passwords match (insecure comparison!)
                }
            }
        } catch (Exception e) {
            // Log the exception (using a proper logging framework is recommended)
            System.err.println("Error during user validation: " + e.getMessage());
            e.printStackTrace(); 
        }

        return null; // User not found or password incorrect
    }
}
