package ir.cinema;

import java.sql.SQLException;

public class FillDataBase {
    private final AdminRepository adminRepository = new AdminRepository();
    private final CinemaRepository cinemaRepository = new CinemaRepository();
    private final TicketRepository ticketRepository = new TicketRepository();
    private final CustomerRepository customerRepository = new CustomerRepository();
    private final ReserveRepository reserveRepository = new ReserveRepository();

    public void createAllTables() throws SQLException {
        adminRepository.createTableAdmin();
        cinemaRepository.CreateTableCinema();
        ticketRepository.CreateTableTicket();
        customerRepository.CreateTableCustomer();
        reserveRepository.CreateReserveTable();
        System.out.println("tables created success!!");
    }
    public void insertData() throws SQLException{
        AdminRegister admin1 = new AdminRegister("hassan", "mohseni", "admin", "admin");
        adminRepository.saveAdmin(admin1);

        CustomerRegister customer1 = new CustomerRegister(
                "0021239273", "ali", "karimi",
                "09350592591", "karimi98", "123456");
        customerRepository.saveCustomer(customer1);
        CustomerRegister customer2 = new CustomerRegister(
                "0021239274", "javad", "maddadi",
                "09102734817", "maddadi98", "123456");
        customerRepository.saveCustomer(customer2);
        CustomerRegister customer3 = new CustomerRegister(
                "0021239275", "hassan", "mohseni",
                "09350592592", "mohseni98", "123456");
        customerRepository.saveCustomer(customer3);
        CustomerRegister customer4 = new CustomerRegister(
                "0021239276", "sara", "salari",
                "09350592593", "salari98", "123456");
        customerRepository.saveCustomer(customer4);

        CinemaRegister cinema1 = new CinemaRegister("koroush", "tehran-satary", "koroush1400", "123456");
        cinemaRepository.saveCinema(cinema1);
        CinemaRegister cinema2 = new CinemaRegister("africa", "tehran-valiasr", "africa1400", "123456");
        cinemaRepository.saveCinema(cinema2);
        CinemaRegister cinema3 = new CinemaRegister("mandana", "tehran-damavand", "mandana1400", "123456");
        cinemaRepository.saveCinema(cinema3);
        CinemaRegister cinema4 = new CinemaRegister("paytkht", "tehran-haftTir", "paytakht1400", "123456");
        cinemaRepository.saveCinema(cinema4);

        Ticket ticket1 = new Ticket(1, "hezarPa", "2022-01-10", "18:00", "20:00",
                50, 30000);
        ticketRepository.saveTicket(ticket1);
        Ticket ticket2 = new Ticket(1, "nahangAnbar", "2022-01-10", "20:00", "22:00",
                50, 30000);
        ticketRepository.saveTicket(ticket2);
        Ticket ticket3 = new Ticket(1, "shishlik", "2022-01-11", "22:00", "24:00",
                50, 30000);
        ticketRepository.saveTicket(ticket3);
        Ticket ticket4 = new Ticket(2, "derakula", "2022-01-10", "17:30", "19:30",
                70, 30000);
        ticketRepository.saveTicket(ticket4);
        Ticket ticket5 = new Ticket(2, "zireNazar", "2022-01-11", "19:30", "21:30",
                70, 30000);
        ticketRepository.saveTicket(ticket5);
        Ticket ticket6 = new Ticket(2, "mosadere", "2022-01-11", "21:30", "23:30",
                70, 30000);
        ticketRepository.saveTicket(ticket6);
        Ticket ticket7 = new Ticket(3, "hezarPa", "2022-01-10", "18:00", "20:00",
                100, 30000);
        ticketRepository.saveTicket(ticket7);
        Ticket ticket8 = new Ticket(3, "nahangAnbar", "2022-01-11", "20:00", "22:00",
                100, 30000);
        ticketRepository.saveTicket(ticket8);
        Ticket ticket9 = new Ticket(4, "derakula", "2022-01-10", "17:30", "19:30",
                100, 30000);
        ticketRepository.saveTicket(ticket9);
        Ticket ticket10 = new Ticket(4, "shishlik", "2022-01-11", "19:30", "21:30",
                100, 30000);
        ticketRepository.saveTicket(ticket10);
        System.out.println("data imported success!!");
    }
}
