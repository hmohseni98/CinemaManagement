package ir.cinema;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository {
    private DatabaseRepository databaseRepository = new DatabaseRepository();
    public void createTableAdmin() throws SQLException{
        databaseRepository.connection();
        Connection dbConnection = databaseRepository.getConnection();
        String createTableAdmin = "create table if not exists admin(" +
                "id serial primary key," +
                "firstname varchar(30)," +
                "lastname varchar (50) ," +
                "username varchar(15) not null," +
                "password varchar(15) not null);";
        dbConnection.prepareStatement(createTableAdmin).execute();
        dbConnection.close();
    }
    public void saveAdmin(AdminRegister adminRegister) throws SQLException {
        String saveAdmin ="insert into admin(firstname,lastname,username,password)" +
                "values (?,?,?,?)";
        databaseRepository.connection();
        Connection dbConnection = databaseRepository.getConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(saveAdmin);
        preparedStatement.setString(1,adminRegister.getFirstName());
        preparedStatement.setString(2,adminRegister.getLastName());
        preparedStatement.setString(3,adminRegister.getUsername());
        preparedStatement.setString(4,adminRegister.getPassword());
        preparedStatement.executeUpdate();
        dbConnection.close();
    }
    public int checkAccount(String username,String password) throws SQLException {
        String checkAccount = "SELECT count(*) FROM admin " +
                "WHERE username = ? AND password = ?";
        databaseRepository.connection();
        Connection dbConnection = databaseRepository.getConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(checkAccount);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ResultSet result = preparedStatement.executeQuery();
        result.next();
        return result.getInt("count");
    }
}
