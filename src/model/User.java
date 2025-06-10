package model;

/**
 * Model class representing a User entity.
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String email;

    /**
     * Constructor for creating a new User.
     * @param username The user's username.
     * @param password The user's password.
     * @param email The user's email.
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}