package ir.cinema;

public class AdminRegister {
    private String firstName;
    private String lastName;
    private String Username;
    private String password;
    public AdminRegister(){}

    public AdminRegister(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        Username = username;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
