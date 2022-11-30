package edu.upc.dsa.minim.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactorySession {
    public static Session openSession() {
        Connection conn = getConnection();

        return new SessionImpl(conn);
    }

    private static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/minim1","root","Ar,a3b3.");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return conn;
    }
}
