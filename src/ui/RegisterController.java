package ui;

import dao.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;

/**
 * Controller for the Register UI, handling user registration.
 */
public class RegisterController {

    @FXML
    private TextField newUsername;

    @FXML
    private TextField newEmail;

    @FXML
    private PasswordField newPassword;

    private UserDAO userDAO = new UserDAO();

    /**
     * Handles the submit button action for user registration.
     */
    @FXML
    private void handleSubmit() {
        String username = newUsername.getText().trim();
        String email = newEmail.getText().trim();
        String password = newPassword.getText();

        // Client-side validation
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "All fields are required.");
            return;
        }
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            showAlert(Alert.AlertType.ERROR, "Invalid email format.");
            return;
        }
        if (password.length() < 6) {
            showAlert(Alert.AlertType.ERROR, "Password must be at least 6 characters.");
            return;
        }

        try {
            User user = new User(username, password, email);
            if (userDAO.registerUser(user)) {
                showAlert(Alert.AlertType.INFORMATION, "User registered successfully.");
                newUsername.clear();
                newEmail.clear();
                newPassword.clear();
            } else {
                showAlert(Alert.AlertType.ERROR, "Registration failed.");
            }
        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Registration failed: " + e.getMessage());
        }
    }

    /**
     * Displays an alert dialog with the specified type and message.
     * @param type The type of alert (e.g., ERROR, INFORMATION).
     * @param message The message to display.
     */
    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}