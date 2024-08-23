public class User {
    private String username;
    private String password;

    //create a constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // getter and setter method for username
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    // getter and setter method for password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // Override toString method for user login details
    @Override
    public String toString() {
        return "User{" +
                " Username='" + username + '\'' +
                ", Password='" + password + '\'' +
                '}';
    }
}
