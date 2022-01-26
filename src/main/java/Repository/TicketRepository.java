package Repository;

import Entity.Ticket;
import Database.myConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketRepository {

    private Connection connection = myConnection.connection;

    public void saveTicket(Ticket ticket) throws SQLException {
        String saveTicket = "insert into ticket(cinema_id,film_name,date_set,start_at,end_at,capacity,price)" +
                "values (?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(saveTicket);
        preparedStatement.setInt(1,ticket.getCinema_id());
        preparedStatement.setString(2,ticket.getFilm_name());
        preparedStatement.setString(3,ticket.getDate());
        preparedStatement.setString(4,ticket.getStartAt());
        preparedStatement.setString(5,ticket.getEndAt());
        preparedStatement.setInt(6,ticket.getCapacity());
        preparedStatement.setFloat(7,ticket.getPrice());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public void listOfTicket() throws SQLException {
        String listOfTicket = "SELECT * FROM ticket " +
                "ORDER BY id";
        PreparedStatement preparedStatement = connection.prepareStatement(listOfTicket);
        ResultSet result = preparedStatement.executeQuery();
        CinemaRepository cinemaRepository = new CinemaRepository();
        while (result.next()){
            System.out.println("ID:" + result.getInt("id") + "   " +
                    "CinemaName:" + cinemaRepository.getNameByID(result.getInt("cinema_id")) + "   " +
                    "FilmName:" + result.getString("film_name") + "   " +
                    "Date:" +result.getString("date_set") + "   " +
                    "StartAt:" +result.getString("start_at") + "   " +
                    "EndAt:" +result.getString("end_at") + "   " +
                    "Capacity:" +result.getInt("capacity") + "   " +
                    "Price:" +result.getFloat("price"));
        }
        preparedStatement.close();
    }
    public void searchByNameAndDate(String filmName,String date) throws SQLException{
        String searchByNameAndDate = "SELECT * FROM ticket " +
                "WHERE film_name = ? AND date_set = ?" +
                "ORDER BY id";
        PreparedStatement preparedStatement = connection.prepareStatement(searchByNameAndDate);
        preparedStatement.setString(1,filmName);
        preparedStatement.setString(2,date);
        ResultSet result = preparedStatement.executeQuery();
        CinemaRepository cinemaRepository = new CinemaRepository();
        while (result.next()){
            System.out.println("ID:" + result.getInt("id") + "   " +
                    "CinemaName:" + cinemaRepository.getNameByID(result.getInt("cinema_id")) + "   " +
                    "FilmName:" + result.getString("film_name") + "   " +
                    "Date:" +result.getString("date_set") + "   " +
                    "StartAt:" +result.getString("start_at") + "   " +
                    "EndAt:" +result.getString("end_at") + "   " +
                    "Capacity:" +result.getInt("capacity") + "   " +
                    "Price:" +result.getFloat("price"));
        }
        preparedStatement.close();
    }
    public int getCapacityById(int id) throws SQLException {
        String getCapacityById = "SELECT capacity FROM ticket " +
                "WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(getCapacityById);
        preparedStatement.setInt(1,id);
        ResultSet result = preparedStatement.executeQuery();
        result.next();
        int res = result.getInt("capacity");
        preparedStatement.close();
        return res;
    }
    public void updateCapacity(int id ,int capacity) throws SQLException {
        String updateCapacity = "UPDATE ticket " +
                "SET capacity = ? " +
                "WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateCapacity);
        preparedStatement.setInt(1,getCapacityById(id) - capacity);
        preparedStatement.setInt(2,id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public void searchTicketByCinema(int id) throws SQLException{
        String searchTicketByCinema = "SELECT * FROM ticket " +
                "WHERE cinema_id =?";
        CinemaRepository cinemaRepository = new CinemaRepository();
        PreparedStatement preparedStatement = connection.prepareStatement(searchTicketByCinema);
        preparedStatement.setInt(1,id);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()){
            System.out.println("ID:" + result.getInt("id") + "   " +
                    "CinemaName:" + cinemaRepository.getNameByID(result.getInt("cinema_id")) + "   " +
                    "FilmName:" + result.getString("film_name") + "   " +
                    "Date:" +result.getString("date_set") + "   " +
                    "StartAt:" +result.getString("start_at") + "   " +
                    "EndAt:" +result.getString("end_at") + "   " +
                    "Capacity:" +result.getInt("capacity") + "   " +
                    "Price:" +result.getFloat("price"));
        }
        preparedStatement.close();
    }
    public String[] getDateAndStartTimeById(int id) throws SQLException{
        String getDateAndStartTimeById = "SELECT date_set,start_at FROM ticket " +
                "WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(getDateAndStartTimeById);
        preparedStatement.setInt(1,id);
        ResultSet result = preparedStatement.executeQuery();
        result.next();
        String[] res = new String[2];
        res[0] = result.getString("date_set");
        res[1] = result.getString("start_at");
        preparedStatement.close();
        return res;
    }
    public void deleteRowById(int id) throws SQLException{
        String deleteRowById = "DELETE FROM ticket " +
                "WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteRowById);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
