package ui;

import dao.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the Login UI, handling login, reset, and register navigation.
 */
public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private UserDAO userDAO = new UserDAO();

    /**
     * Handles login button action with validation and authentication.
     */
    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Username and password are required.");
            return;
        }

        try {
            if (userDAO.validateUser(username, password)) {
                showAlert(Alert.AlertType.INFORMATION, "Login successful!");
                // Redirect to a dashboard or main app screen (not implemented)
            } else {
                showAlert(Alert.AlertType.ERROR, "Invalid username or password.");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Login failed: " + e.getMessage());
        }
    }

    /**
     * Clears the input fields on reset button click.
     */
    @FXML
    private void handleReset() {
        usernameField.clear();
        passwordField.clear();
    }

    /**
     * Opens the registration window.
     */
    @FXML
    private void handleShowRegister() {
        try {
            Parent registerRoot = FXMLLoader.load(getClass().getResource("/ui/Register.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Register");
            stage.setScene(new Scene(registerRoot, 400, 300));
            stage.show();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Failed to open registration: " + e.getMessage());
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