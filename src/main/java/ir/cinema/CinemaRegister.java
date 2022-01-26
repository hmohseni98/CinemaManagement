package ir.cinema;

public class CinemaRegister {
    private String name;
    private String address;
    private String username;
    private String password;
    private boolean authentication;

    public CinemaRegister(){}
    public CinemaRegister(String name, String address, String username, String password) {
        this.name = name;
        this.address = address;
        this.username = username;
        this.password = password;
        this.authentication = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthentication() {
        return authentication;
    }

    public void setAuthentication(boolean authentication) {
        this.authentication = authentication;
    }
}
