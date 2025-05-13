package com.gokart.model;

// import java.util.Date; // Using String for birthday for now

public class UserModel {

    private int userId; // Assuming there's a user ID from the database
    private String firstName;
    private String lastName;
    private String username;
    private String birthday; // Using String for simplicity
    private String gender;
    private String email;
    private String phoneNumber;
    private String password; // Store hashed password
    private String role;


    // Default constructor
    public UserModel() {
    }

    // Parameterized constructor
    public UserModel(String firstName, String lastName, String username, 
                    String birthday, String gender, String email, 
                    String phoneNumber, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.birthday = birthday;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public int getUserID() {
        return userId;
    }

    public void setUserID(int userID) {
        this.userId = userID;
    }

    // Getter and Setter for firstName
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter and Setter for lastName
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter and Setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and Setter for birthday
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    // Getter and Setter for gender
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter for phoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for role
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
	
    

	
}
