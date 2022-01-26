package ir.cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReserveRepository {
    DatabaseRepository databaseRepository = new DatabaseRepository();
    public void CreateReserveTable() throws SQLException {
        databaseRepository.connection();
        Connection dbConnection = databaseRepository.getConnection();
        String createReserveTable = "create table if not exists reserve_ticket( " +
                "id serial primary key," +
                "ticket_id integer ," +
                "customer_id char(10) ," +
                "count integer ," +
                "total_price float ," +
                "constraint fk_ticket foreign key (ticket_id) references ticket(id) ," +
                "constraint fk_customer foreign key (customer_id) references customer(national_code));";
        dbConnection.prepareStatement(createReserveTable).execute();
        dbConnection.close();
    }
    public void saveReserve(Reserve reserve) throws SQLException {
        String saveReserve = "insert into reserve_ticket(ticket_id, customer_id, count, total_price)" +
                "values (?,?,?,?)";
        databaseRepository.connection();
        Connection dbConnection = databaseRepository.getConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(saveReserve);
        preparedStatement.setInt(1,reserve.getTicket_id());
        preparedStatement.setInt(2,reserve.getCustomer_id());
        preparedStatement.setInt(3,reserve.getCount());
        preparedStatement.setFloat(4,reserve.getTotal_price());
        preparedStatement.executeUpdate();
        dbConnection.close();
    }
}
