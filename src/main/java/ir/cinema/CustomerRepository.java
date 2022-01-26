package ir.cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class CustomerRepository {
    private final DatabaseRepository databaseRepository = new DatabaseRepository();
    public void CreateTableCustomer() throws SQLException {
        databaseRepository.connection();
        Connection dbConnection = databaseRepository.getConnection();
        String createTableCustomer = "create table if not exists customer(" +
                "national_code char(10) primary key," +
                "firstname varchar(30)," +
                "lastname varchar(50)," +
                "phone_number char(11)," +
                "username varchar(15) not null ," +
                "password varchar(15) not null);";
        dbConnection.prepareStatement(createTableCustomer).execute();
        dbConnection.close();
    }
   /* public void CreateTableTicketCustomer() throws SQLException{
        databaseRepository.connection();
        Connection dbConnection = databaseRepository.getConnection();
        String createTableTicketCustomer = "create table if not exists ticket_customer(" +
                "id serial primary key," +
                "ticket_id integer," +
                "customer_id char(10)," +
                "constraint fk_ticket foreign key (ticket_id) references ticket(id)," +
                "constraint fk_customer foreign key (customer_id) references customer(national_code));";
        dbConnection.prepareStatement(createTableTicketCustomer).execute();
        dbConnection.close();
    }*/
    public void saveCustomer(CustomerRegister customerRegister) throws SQLException {
        String saveCustomer ="insert into customer(national_code,firstname,lastname,phone_number,username,password)" +
                "values (?,?,?,?,?,?)";
        databaseRepository.connection();
        Connection dbConnection = databaseRepository.getConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(saveCustomer);
        preparedStatement.setString(1,customerRegister.getNationalCode());
        preparedStatement.setString(2,customerRegister.getFirstName());
        preparedStatement.setString(3,customerRegister.getLastName());
        preparedStatement.setString(4,customerRegister.getPhoneNumber());
        preparedStatement.setString(5,customerRegister.getUsername());
        preparedStatement.setString(6,customerRegister.getPassword());
        preparedStatement.executeUpdate();
        dbConnection.close();
    }
    public int checkAccount(String username,String password) throws SQLException {
        String CheckAccount = "SELECT count(*) FROM customer " +
                "WHERE username = ? AND password = ?";
        databaseRepository.connection();
        Connection dbConnection = databaseRepository.getConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(CheckAccount);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ResultSet result = preparedStatement.executeQuery();
        result.next();
        return result.getInt("count");
    }
}
