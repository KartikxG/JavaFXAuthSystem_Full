package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class to launch the JavaFX application.
 */
public class Main extends Application {

    /**
     * Starts the JavaFX application by loading the Login UI.
     * @param primaryStage The primary stage for the application.
     * @throws Exception If the FXML loading fails.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ui/Login.fxml"));
        primaryStage.setTitle("Login System");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.setResizable(false); // Prevent window resizing
        primaryStage.show();
    }

    /**
     * Main method to launch the application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}