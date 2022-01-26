package Repository;

import Entity.CustomerRegister;
import Database.myConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class CustomerRepository {

    private Connection connection = myConnection.connection;

    public void saveCustomer(CustomerRegister customerRegister) throws SQLException {
        String saveCustomer ="insert into customer(national_code,firstname,lastname,phone_number,username,password)" +
                "values (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(saveCustomer);
        preparedStatement.setString(1,customerRegister.getNationalCode());
        preparedStatement.setString(2,customerRegister.getFirstName());
        preparedStatement.setString(3,customerRegister.getLastName());
        preparedStatement.setString(4,customerRegister.getPhoneNumber());
        preparedStatement.setString(5,customerRegister.getUsername());
        preparedStatement.setString(6,customerRegister.getPassword());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public int checkAccount(String username,String password) throws SQLException {
        String CheckAccount = "SELECT count(*) FROM customer " +
                "WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(CheckAccount);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ResultSet result = preparedStatement.executeQuery();
        result.next();
        int res = result.getInt("count");
        preparedStatement.close();
        return res;
    }
}
