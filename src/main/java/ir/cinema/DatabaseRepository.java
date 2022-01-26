package ir.cinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseRepository {
    private Connection connection;

    public void connection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres","123456");
    }
    public Connection getConnection() {
        return connection;
    }
    /*public void CreateTableAdmin() throws SQLException{
        String createTableAdmin = "create table if not exists admin(" +
                "id serial primary key," +
                "firstname varchar(30)," +
                "lastname varchar (50) ," +
                "surname varchar(50)," +
                "username varchar(15) not null," +
                "password varchar(15) not null);";
        connection.prepareStatement(createTableAdmin).execute();
        connection.close();
    }
    public void CreateTableCinema() throws SQLException {
        String createTableCinema = "create table if not exists cinema(" +
                "id serial primary key," +
                "admin_id integer," +
                "name varchar(50)," +
                "address varchar(100)," +
                "capacity integer," +
                "username varchar(15) not null," +
                "password varchar(15) not null," +
                "constraint fk_admin foreign key (admin_id) references admin(id));";
        connection.prepareStatement(createTableCinema).execute();
        connection.close();
    }
    public void CreateTableTicket() throws SQLException{
        String createTableTicket = "create table if not exists ticket(" +
                "id serial primary key," +
                "film_name varchar(30) not null," +
                "date date not null, "+
                "start_at TIME not null," +
                "end_at TIME not null);";
        connection.prepareStatement(createTableTicket);
        connection.close();
    }
    public void CreateTableCinemaTicket() throws SQLException{
        String createTableCinemaTicket = "create table if not exists cinema_ticket(" +
                "id serial primary key," +
                "ticket_id integer," +
                "cinema_id integer," +
                "constraint fk_ticket foreign key (ticket_id) references ticket(id)," +
                "constraint fk_cinema foreign key (cinema_id) references cinema(id));";
        connection.prepareStatement(createTableCinemaTicket);
        connection.close();
    }
    public void CreateTableUser() throws SQLException{
        String createTableUser = "create table if not exists user(" +
                "national_code char(10) primary key," +
                "firstname varchar(30)," +
                "lastname varchar(50)," +
                "phone_number char(11)," +
                "username varchar(15) not null ," +
                "password varchar(15)) not null ;";
        connection.prepareStatement(createTableUser);
        connection.close();
    }
    public void CreateTableTicketUser() throws SQLException{
        String createTableTicketUser = "create table if not exists ticket_user()" +
                "id serial primary key," +
                "ticket_id integer," +
                "user_id integer," +
                "constraint fk_ticket foreign key (ticket_id) references ticket(id)," +
                "constraint fk_user foreign key (user_id) references user(national_code)";
        connection.prepareStatement(createTableTicketUser);
        connection.close();
    }*/
}
