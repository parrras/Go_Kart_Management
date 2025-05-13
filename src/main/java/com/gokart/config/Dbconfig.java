package com.gokart.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconfig {

    // Database configuration
    private static final String DB_NAME = "gokart_db";
    private static final String URL = "jdbc:mysql://localhost:3307/" + DB_NAME;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    /**
     * Establishes and returns a database connection.
     *
     * @return Connection object, or null if connection fails
     */
    public static Connection getDbConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
        }
        return connection;
    }
}
