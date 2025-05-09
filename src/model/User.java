package model;

public class User {
    private final String email;
    private final String username;
    private final String password;
    private final String phone;

    public User(String email, String username, String password, String phone) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    // Getters
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
}