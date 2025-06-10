package dao;

import model.User;
import utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object for User-related database operations.
 */
public class UserDAO {

    /**
     * Registers a new user in the database with validation.
     * @param user The User object containing username, password, and email.
     * @return true if registration is successful, false otherwise.
     * @throws IllegalArgumentException if input validation fails.
     */
    public boolean registerUser(User user) {
        // Validate inputs
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (user.getEmail() == null || !user.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
        if (isUsernameTaken(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (isEmailTaken(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword()); // In production, hash the password
            stmt.setString(3, user.getEmail());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Database error during registration: " + e.getMessage(), e);
        }
    }

    /**
     * Validates user credentials against the database.
     * @param username The username to validate.
     * @param password The password to validate.
     * @return true if credentials are valid, false otherwise.
     */
    public boolean validateUser(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password); // In production, compare hashed password
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Database error during validation: " + e.getMessage(), e);
        }
    }

    /**
     * Checks if a username already exists in the database.
     * @param username The username to check.
     * @return true if the username is taken, false otherwise.
     */
    private boolean isUsernameTaken(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Database error checking username: " + e.getMessage(), e);
        }
    }

    /**
     * Checks if an email already exists in the database.
     * @param email The email to check.
     * @return true if the email is taken, false otherwise.
     */
    private boolean isEmailTaken(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Database error checking email: " + e.getMessage(), e);
        }
    }
}