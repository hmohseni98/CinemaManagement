package Service;

import Entity.Ticket;
import Repository.TicketRepository;

import java.sql.SQLException;

public class TicketService {
    private TicketRepository ticketRepository = new TicketRepository();

    public void saveTicket(Ticket ticket) throws SQLException {
        ticketRepository.saveTicket(ticket);
    }

    public void listOfTicket() throws SQLException {
        ticketRepository.listOfTicket();
    }

    public void searchByNameAndDate(String filmName, String date) throws SQLException {
        ticketRepository.searchByNameAndDate(filmName, date);
    }

    public int getCapacityById(int id) throws SQLException {
        return ticketRepository.getCapacityById(id);
    }

    public void updateCapacity(int id, int capacity) throws SQLException {
        ticketRepository.updateCapacity(id, capacity);
    }

    public void searchTicketByCinema(int id) throws SQLException {
        ticketRepository.searchTicketByCinema(id);
    }

    public String[] getDateAndStartTimeById(int id) throws SQLException {
        return ticketRepository.getDateAndStartTimeById(id);
    }

    public void deleteRowById(int id) throws SQLException {
        ticketRepository.deleteRowById(id);
    }
}
