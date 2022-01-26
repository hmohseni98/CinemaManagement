package Repository;

import Entity.CinemaRegister;
import Database.myConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CinemaRepository {
    private Connection connection = myConnection.connection;

    public void saveCinema(CinemaRegister cinemaRegister) throws SQLException {
        String saveCinema = "insert into cinema(name,address,username,password,authentication)" +
                "values (?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(saveCinema);
        preparedStatement.setString(1,cinemaRegister.getName());
        preparedStatement.setString(2,cinemaRegister.getAddress());
        preparedStatement.setString(3,cinemaRegister.getUsername());
        preparedStatement.setString(4,cinemaRegister.getPassword());
        preparedStatement.setBoolean(5,cinemaRegister.isAuthentication());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public int checkAccount(String username,String password) throws SQLException {
        String checkAccount = "SELECT count(*) FROM cinema " +
                "WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(checkAccount);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ResultSet result = preparedStatement.executeQuery();
        result.next();
        int res =result.getInt("count");
        preparedStatement.close();
        return res;
    }

    public void listOfCinema() throws SQLException {
        String listOfCinema = "SELECT id,name,address,authentication FROM cinema " +
                "ORDER BY id";
        PreparedStatement preparedStatement = connection.prepareStatement(listOfCinema);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()){
            System.out.println("ID:" + result.getInt("id") + "   " +
                    "Name:" +result.getString("name") + "   " +
                    "Address:" +result.getString("address") + "   " +
                    "StatusAuthentication:" +result.getBoolean("authentication"));
        }
        preparedStatement.close();
    }
    public int authenticate(int id) throws SQLException {
        String authenticate = "UPDATE cinema " +
                "SET authentication = true " +
                "WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(authenticate);
        preparedStatement.setInt(1,id);
        int result = preparedStatement.executeUpdate();
        return result;
    }
    public int checkAuthenticationStatus(String username) throws SQLException {
        String checkAuthenticationStatus = "SELECT count(*) FROM cinema " +
                "WHERE username = ? AND authentication = true";
        PreparedStatement preparedStatement = connection.prepareStatement(checkAuthenticationStatus);
        preparedStatement.setString(1,username);
        ResultSet result = preparedStatement.executeQuery();
        result.next();
        int res =result.getInt("count");
        preparedStatement.close();
        return res;
    }
    public int getIdByUsername(String username) throws SQLException {
        String getIdByUsername = "SELECT * FROM cinema " +
                "WHERE username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(getIdByUsername);
        preparedStatement.setString(1,username);
        ResultSet result = preparedStatement.executeQuery();
        result.next();
        int res = result.getInt("id");
        preparedStatement.close();
        return res;
    }
    public String getNameByID(int id) throws SQLException{
        String getIdByUsername = "SELECT * FROM cinema " +
                "WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(getIdByUsername);
        preparedStatement.setInt(1,id);
        ResultSet result = preparedStatement.executeQuery();
        result.next();
        String res = result.getString("name");
        preparedStatement.close();
        return res;
    }
}
