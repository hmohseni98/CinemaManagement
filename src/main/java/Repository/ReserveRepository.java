package Repository;

import Entity.Reserve;
import Database.myConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReserveRepository {
    private Connection connection = myConnection.connection;

    public void saveReserve(Reserve reserve) throws SQLException {
        String saveReserve = "insert into reserve_ticket(ticket_id, customer_id, count, total_price)" +
                "values (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(saveReserve);
        preparedStatement.setInt(1,reserve.getTicket_id());
        preparedStatement.setInt(2,reserve.getCustomer_id());
        preparedStatement.setInt(3,reserve.getCount());
        preparedStatement.setFloat(4,reserve.getTotal_price());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
