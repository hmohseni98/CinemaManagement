package ir.cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CinemaRepository {
    private final DatabaseRepository databaseRepository = new DatabaseRepository();
    public void CreateTableCinema() throws SQLException {
        databaseRepository.connection();
        Connection dbConnection = databaseRepository.getConnection();
        String createTableCinema = "create table if not exists cinema(" +
                "id serial primary key," +
                "name varchar(50)," +
                "address varchar(100)," +
                "username varchar(15) not null," +
                "password varchar(15) not null," +
                "authentication boolean);";
        dbConnection.prepareStatement(createTableCinema).execute();
        dbConnection.close();
    }
    public void saveCinema(CinemaRegister cinemaRegister) throws SQLException {
        String saveCinema = "insert into cinema(name,address,username,password,authentication)" +
                "values (?,?,?,?,?)";
        databaseRepository.connection();
        Connection dbConnection = databaseRepository.getConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(saveCinema);
        preparedStatement.setString(1,cinemaRegister.getName());
        preparedStatement.setString(2,cinemaRegister.getAddress());
        preparedStatement.setString(3,cinemaRegister.getUsername());
        preparedStatement.setString(4,cinemaRegister.getPassword());
        preparedStatement.setBoolean(5,cinemaRegister.isAuthentication());
        preparedStatement.executeUpdate();
        dbConnection.close();
    }
    public int checkAccount(String username,String password) throws SQLException {
        String checkAccount = "SELECT count(*) FROM cinema " +
                "WHERE username = ? AND password = ?";
        databaseRepository.connection();
        Connection dbConnection = databaseRepository.getConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(checkAccount);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ResultSet result = preparedStatement.executeQuery();
        result.next();
        int res =result.getInt("count");
        dbConnection.close();
        return res;


    }
    public void listOfCinema() throws SQLException {
        String listOfCinema = "SELECT id,name,address,authentication FROM cinema " +
                "ORDER BY id";
        databaseRepository.connection();
        Connection dbConnection = databaseRepository.getConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(listOfCinema);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()){
            System.out.println("ID:" + result.getInt("id") + "   " +
                    "Name:" +result.getString("name") + "   " +
                    "Address:" +result.getString("address") + "   " +
                    "StatusAuthentication:" +result.getBoolean("authentication"));
        }
        dbConnection.close();
    }
    public int authenticate(int id) throws SQLException {
        String authenticate = "UPDATE cinema " +
                "SET authentication = true " +
                "WHERE id = ?";
        databaseRepository.connection();
        Connection dbConnection = databaseRepository.getConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(authenticate);
        preparedStatement.setInt(1,id);
        int result = preparedStatement.executeUpdate();
        return result;
    }
    public int checkAuthenticationStatus(String username) throws SQLException {
        String checkAuthenticationStatus = "SELECT count(*) FROM cinema " +
                "WHERE username = ? AND authentication = true";
        databaseRepository.connection();
        Connection dbConnection = databaseRepository.getConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(checkAuthenticationStatus);
        preparedStatement.setString(1,username);
        ResultSet result = preparedStatement.executeQuery();
        result.next();
        int res =result.getInt("count");
        dbConnection.close();
        return res;
    }
    public int getIdByUsername(String username) throws SQLException {
        String getIdByUsername = "SELECT * FROM cinema " +
                "WHERE username = ?";
        databaseRepository.connection();
        Connection dbConnection = databaseRepository.getConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(getIdByUsername);
        preparedStatement.setString(1,username);
        ResultSet result = preparedStatement.executeQuery();
        result.next();
        int res = result.getInt("id");
        dbConnection.close();
        return res;
    }
    public String getNameByID(int id) throws SQLException{
        String getIdByUsername = "SELECT * FROM cinema " +
                "WHERE id = ?";
        databaseRepository.connection();
        Connection dbConnection = databaseRepository.getConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(getIdByUsername);
        preparedStatement.setInt(1,id);
        ResultSet result = preparedStatement.executeQuery();
        result.next();
        String res = result.getString("name");
        dbConnection.close();
        return res;
    }
}
