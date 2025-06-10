package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for database connection management.
 */
public class DbUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/auth";
    private static final String USER = "root";
    private static final String PASSWORD = "tanmay098";

    /**
     * Establishes a connection to the MySQL database.
     * @return A Connection object.
     * @throws SQLException If a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("Failed to connect to database: " + e.getMessage(), e);
        }
    }
}