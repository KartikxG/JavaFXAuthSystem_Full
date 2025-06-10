# JavaFX User Authentication System

## Overview
This is a JavaFX-based user authentication system that includes:
- User registration with validation
- User login with authentication
- Database connectivity with JDBC (MySQL)
- Enhanced GUI with CSS styling
- Robust error handling and data validation

## Project Structure
```
JavaFXAuthSystem_Full/
├── lib/
│   └── mysql-connector-j-9.3.0.jar
├── src/
│   ├── dao/
│   │   └── UserDAO.java
│   ├── model/
│   │   └── User.java
│   ├── ui/
│   │   ├── Login.fxml
│   │   ├── LoginController.java
│   │   ├── Main.java
│   │   ├── Register.fxml
│   │   ├── RegisterController.java
│   │   └── styles.css
│   └── utils/
│       └── DbUtil.java
├── auth_schema.sql
├── JavaFXAuthSystem_Full.iml
└── README.md
```

## Features
- **User Registration**: Register with a unique username, email, and password (minimum 6 characters).
- **User Login**: Authenticate using username and password.
- **Data Validation**: Client-side and server-side validation for inputs (e.g., email format, username uniqueness).
- **Error Handling**: Robust exception handling with user-friendly alerts.
- **GUI**: Modern UI with CSS styling, including hover effects and consistent layout.
- **Event Handling**: Optimized event listeners for efficient performance.

## How to Run
1. **Prerequisites**:
   - Java 17 or higher
   - IntelliJ IDEA
   - MySQL Server
   - JavaFX SDK 17 (ensure it's in the `lib` folder or configured in IntelliJ)

2. **Database Setup**:
   - Start MySQL Server.
   - Execute the SQL in `auth_schema.sql` to create the `auth` database and `users` table:
     ```sql
     CREATE DATABASE IF NOT EXISTS auth;
     USE auth;
     CREATE TABLE IF NOT EXISTS users (
         id INT AUTO_INCREMENT PRIMARY KEY,
         username VARCHAR(255) UNIQUE NOT NULL,
         password VARCHAR(255) NOT NULL,
         email VARCHAR(255) UNIQUE NOT NULL
     );
     ```
   - Update `DbUtil.java` with your MySQL credentials if different (default: `root`/`tanmay098`).

3. **Project Setup in IntelliJ IDEA**:
   - Open the project in IntelliJ IDEA.
   - Ensure the MySQL JDBC connector (`mysql-connector-j-9.3.0.jar`) is in the `lib` folder and added to the project:
     - Go to `File > Project Structure > Libraries`.
     - Click `+`, select `Java`, and choose `mysql-connector-j-9.3.0.jar` from the `lib` folder.
   - Configure JavaFX:
     - Go to `File > Project Structure > Libraries`.
     - Add JavaFX SDK 17 if not already present.
     - Update the `.iml` file if needed (already provided).
   - Set up the run configuration:
     - Go to `Run > Edit Configurations`.
     - Add a new `Application` configuration.
     - Set `Main class` to `ui.Main`.
     - Add VM options: `--module-path path/to/javafx-sdk-17/lib --add-modules javafx.controls,javafx.fxml`

4. **Run the Application**:
   - Run `Main.java` using the configured run configuration.
   - The login window will appear. Use the "Register" button to create a new account or log in with existing credentials.

## Usage
- **Login**:
  - Enter a username and password.
  - Click "Login" to authenticate.
  - Use "Reset" to clear the fields.
  - Click "Register" to open the registration window.
- **Register**:
  - Enter a unique username, valid email, and password (minimum 6 characters).
  - Click "Submit" to register.
  - Alerts will inform you of success or errors.

## Notes
- Passwords are stored in plain text for simplicity. In a production environment, use a hashing algorithm (e.g., BCrypt).
- The application window is not resizable to maintain consistent UI layout.
- Error handling ensures the application does not crash on invalid inputs or database issues.
- The MySQL JDBC connector version is updated to 9.3.0 (as seen in the screenshot).

## Future Improvements
- Implement password hashing with BCrypt.
- Add a dashboard after successful login.
- Support password recovery.
- Enhance UI with additional themes.

## Troubleshooting
- **Database Connection Error**: Ensure MySQL Server is running and credentials in `DbUtil.java` are correct.
- **JavaFX Not Found**: Verify the JavaFX SDK path in the run configuration and `.iml` file.
- **JDBC Driver Not Found**: Ensure `mysql-connector-j-9.3.0.jar` is in the `lib` folder and added to the project libraries.