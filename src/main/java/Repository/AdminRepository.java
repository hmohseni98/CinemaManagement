package Repository;


import Entity.AdminRegister;
import Database.myConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository {
    private Connection connection = myConnection.connection;

    public void saveAdmin(AdminRegister adminRegister) throws SQLException {
        String saveAdmin ="insert into admin(firstname,lastname,username,password)" +
                "values (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(saveAdmin);
        preparedStatement.setString(1,adminRegister.getFirstName());
        preparedStatement.setString(2,adminRegister.getLastName());
        preparedStatement.setString(3,adminRegister.getUsername());
        preparedStatement.setString(4,adminRegister.getPassword());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public int checkAccount(String username,String password) throws SQLException {
        String checkAccount = "SELECT count(*) FROM admin " +
                "WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(checkAccount);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ResultSet result = preparedStatement.executeQuery();
        result.next();
        return result.getInt("count");
    }
}
