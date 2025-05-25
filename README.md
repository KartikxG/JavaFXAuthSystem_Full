# JavaFX User Authentication System

## Overview
This is a JavaFX-based user authentication system that includes:
- User registration
- Login
- Password hashing with BCrypt
- Database connectivity with JDBC (MySQL)

## How to Run
1. Open this project in IntelliJ IDEA.
2. Make sure MySQL is running and a database named `auth` is already created.
3. Execute the SQL in `auth_schema.sql` to create required tables.
4. Add the JDBC connector JAR (e.g., `mysql-connector-j-8.0.xx.jar`) to the `lib` folder and link it in IntelliJ.
5. Run the `Main.java` file.

## Requirements
- Java 17 or compatible
- JavaFX SDK
- MySQL Server
- JDBC Driver
