public class User {
    private String username;
    private String password;

    public User(String username, String password) { // Constructor to initialize User object with a username and password
        this.username = username;
        this.password = password;
    }

    public String getUsername() { // Getter method to retrieve the username of the user
        return username;
    }

    public String getPassword() { // Getter method to retrieve the password of the user
        return password;
    }

    public void setUsername(String username) { // Setter method to update or set the username of the user
        this.username = username;
    }

    public void setPassword(String password) { // Setter method to update or set the password of the user
        this.password = password;
    }
}
