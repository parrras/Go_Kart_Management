package com.gokart.test;

import com.gokart.config.Dbconfig;
import java.sql.Connection;
import java.sql.SQLException;

public class DbConnectionTest {
    public static void main(String[] args) {
        try {
            System.out.println("Testing database connection...");
            Connection conn = Dbconfig.getDbConnection();
            System.out.println("Connection successful!");
            System.out.println("Database: " + conn.getMetaData().getDatabaseProductName());
            System.out.println("Version: " + conn.getMetaData().getDatabaseProductVersion());
            conn.close();
        } catch (Exception e) {
            System.err.println("Connection failed!");
            e.printStackTrace();
        }
    }
}
