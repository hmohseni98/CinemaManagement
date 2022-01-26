package ir.cinema;


import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        FillDataBase fillDataBase = new FillDataBase();
        //fillDataBase.createAllTables(); //faghat bar aval ejra shavad baad comment shavad chon data tekrar mishavad va error primary key midahad.
        //fillDataBase.insertData(); //faghat bar aval ejra shavad baad comment shavad chon data tekrar mishavad va error primary key midahad.
        boolean isContinue = true;
        while (isContinue) {
            System.out.println("Please select one option");
            System.out.println("1.admin");
            System.out.println("2.cinema");
            System.out.println("3.customer");
            System.out.println("4.exit");
            Scanner scanner = new Scanner(System.in);
            int number;
            System.out.print("Enter number:");
            number = scanner.nextInt();
            if (number == 4)
                isContinue = false;
            else
                accountMenu(number);
        }
    }
    public static void customerMenu() throws SQLException{
        boolean isContinue = true;
        while (isContinue) {
            System.out.println("Please select one option");
            System.out.println("1.Show list of ticket");
            System.out.println("2.Search By film name and date");
            System.out.println("3.Reserve ticket");
            System.out.println("4.Exit");
            Scanner scanner = new Scanner(System.in);
            int number;
            System.out.print("Enter number:");
            number = scanner.nextInt();
            if (number == 1) {
                TicketRepository ticketRepository = new TicketRepository();
                ticketRepository.listOfTicket();
            }
            else if (number == 2){
                System.out.print("Enter film name:");
                String filmName = scanner.next();
                System.out.print("Enter date(ex:2022-02-10):");
                String date = scanner.next();
                TicketRepository ticketRepository = new TicketRepository();
                ticketRepository.searchByNameAndDate(filmName,date);
            }
            else if (number == 3){
                System.out.print("Enter ticket id:");
                int ticket_id = scanner.nextInt();
                System.out.print("Enter number of ticket:");
                int count = scanner.nextInt();
                TicketRepository ticketRepository = new TicketRepository();
                if (count <= ticketRepository.getCapacityById(ticket_id)){
                    ticketRepository.updateCapacity(ticket_id,count);
                    System.out.println("success");
                }
                else
                    System.out.println("out of capacity!");
            }
            else if (number == 4){
                isContinue = false;
            }
            else
                System.out.println("error");
        }
    }
    public static void cinemaMenu(int cinemaId) throws SQLException{
        boolean isContinue = true;
        while (isContinue) {
            System.out.println("Please select one option");
            System.out.println("1.Define new ticket");
            System.out.println("2.Show your cinema ticket");
            System.out.println("3.Cancellation ticket");
            System.out.println("4.Exit");
            Scanner scanner = new Scanner(System.in);
            int number;
            System.out.print("Enter number:");
            number = scanner.nextInt();
            if (number == 1) {
                System.out.print("Enter Film Name:");
                String filmName = scanner.next();
                System.out.print("Enter Date(ex:2022-02-11):");
                String date = scanner.next();
                System.out.print("Enter Start Time(ex:18:00):");
                String startAt = scanner.next();
                System.out.print("Enter End Time(ex:20:00):");
                String endAt = scanner.next();
                System.out.print("Enter Capacity:");
                int capacity = scanner.nextInt();
                System.out.print("Enter Price Ticket:");
                float price = scanner.nextFloat();
                Ticket newTicket = new Ticket(cinemaId, filmName, date, startAt, endAt, capacity, price);
                TicketRepository ticketRepository = new TicketRepository();
                ticketRepository.saveTicket(newTicket);
                System.out.println("success");
            }
            else if (number == 2){
                TicketRepository ticketRepository = new TicketRepository();
                ticketRepository.searchTicketByCinema(cinemaId);
            }
            else if (number == 3){
                System.out.print("Enter your ticket id for check:");
                int ticket_id = scanner.nextInt();
                TicketRepository ticketRepository = new TicketRepository();
                String[] result = new String[2];
                result = ticketRepository.getDateAndStartTimeById(ticket_id);
                LocalDate date_ticket = LocalDate.parse(result[0]);
                LocalTime time_ticket = LocalTime.parse(result[1]);
                if(LocalDate.now().isBefore(date_ticket)) {
                    if (LocalTime.now().isBefore(time_ticket)) {
                        ticketRepository.deleteRowById(ticket_id);
                        System.out.println("success");
                    }
                    else
                        System.out.println("you can not delete ticket");
                }
                else
                    System.out.println("you can not delete ticket");
            }
            else if (number == 4)
                isContinue = false;
            else
                System.out.println("error");
        }
    }
    public static void adminMenu() throws SQLException {
        CinemaRepository cinemaRepository = new CinemaRepository();
        int number;
        Scanner scanner = new Scanner(System.in);
        boolean isContinue = true;
        while(isContinue) {
            System.out.println("Please select one option");
            System.out.println("1.show list of cinema:");
            System.out.println("2.authenticate cinema account:");
            System.out.println("3.exit");
            System.out.print("Enter number:");
            number = scanner.nextInt();
            if (number == 1) {
                cinemaRepository.listOfCinema();
            } else if (number == 2) {
                System.out.print("Enter cinema ID:");
                number = scanner.nextInt();
                if (cinemaRepository.authenticate(number) == 1)
                    System.out.println("success");
                else
                    System.out.println("error");
            } else if (number == 3)
                isContinue = false;
            else
                System.out.println("error");
        }
    }
    public static boolean checkValidation(String type,String username, String password) throws SQLException {
        if (type.equals("admin")){
            AdminRepository adminRepository = new AdminRepository();
            return adminRepository.checkAccount(username, password) == 1;
        }
        else if(type.equals("cinema")){
            CinemaRepository cinemaRepository = new CinemaRepository();
            return cinemaRepository.checkAccount(username, password) == 1;
        }
        else if(type.equals("customer")){
            CustomerRepository customerRepository = new CustomerRepository();
            return customerRepository.checkAccount(username, password) == 1;
        }
        return false;
    }
    public static void accountMenu(int number) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        if (number == 1){ //admin
            System.out.println("Please select one option:");
            System.out.println("1.sign in (help!!! username:admin password:admin)");
            System.out.println("2.sign up");
            System.out.print("Enter number:");
            int inputNumber = scanner.nextInt();
            String username;
            String password;
            if (inputNumber == 1){ // admin sign in
                System.out.print("username:");
                username = scanner.next();
                System.out.print("password:");
                password = scanner.next();
                if (checkValidation("admin", username, password))
                    adminMenu();
                else
                    System.out.println("invalid account");
            }
            if (inputNumber == 2){ //admin sign up
                System.out.print("Enter first name:");
                String firstName = scanner.next();
                System.out.print("Enter last name:");
                String lastName = scanner.next();
                System.out.print("Enter username:");
                String in_username = scanner.next();
                System.out.print("Enter password:");
                String in_password = scanner.next();
                AdminRegister newAdmin = new AdminRegister(firstName,lastName,in_username,in_password);
                AdminRepository adminRepository = new AdminRepository();
                adminRepository.saveAdmin(newAdmin);
                System.out.println("success");
            }
        }
        else if (number == 2){ //cinema
            CinemaRepository cinemaRepository = new CinemaRepository();
            System.out.println("Please select one option:");
            System.out.println("1.sign in (help!!! username:koroush1400 password:123456)");
            System.out.println("2.sign up");
            System.out.print("Enter number:");
            int inputNumber = scanner.nextInt();
            String username;
            String password;
            if (inputNumber == 1){ // cinema sign in
                System.out.print("username:");
                username = scanner.next();
                System.out.print("password:");
                password = scanner.next();
                if (checkValidation("cinema", username, password)){
                    if (cinemaRepository.checkAuthenticationStatus(username) == 1)
                        cinemaMenu(cinemaRepository.getIdByUsername(username));
                    else
                        System.out.println("your account not authorized");
                }
                else
                    System.out.println("invalid account");
            }
            if (inputNumber == 2){ //cinema sign up
                System.out.print("Enter cinema name:");
                String name = scanner.next();
                System.out.print("Enter address:");
                scanner.next();
                String address = scanner.nextLine();
                System.out.print("Enter username:");
                String in_username = scanner.next();
                System.out.print("Enter password:");
                String in_password = scanner.next();
                CinemaRegister newCinema = new CinemaRegister(name,address,in_username,in_password);
                CinemaRepository cinemaRepository2 = new CinemaRepository();
                cinemaRepository2.saveCinema(newCinema);
                System.out.println("success");
            }
        }
        else if (number == 3){ //customer
            System.out.println("Please select one option:");
            System.out.println("1.sign in (help!!! username:mohseni98 password:123456)");
            System.out.println("2.sign up");
            System.out.print("Enter number:");
            int inputNumber = scanner.nextInt();
            String username;
            String password;
            if (inputNumber == 1){ // customer sign in
                System.out.print("username:");
                username = scanner.next();
                System.out.print("password:");
                password = scanner.next();
                if (checkValidation("customer", username, password))
                    customerMenu();
                else
                    System.out.println("invalid account");
            }
            if (inputNumber == 2){ //customer sign up
                System.out.print("Enter national Code:");
                String nationalCode = scanner.next();
                System.out.print("Enter first name:");
                String firstName = scanner.next();
                System.out.print("Enter last name:");
                String lastName = scanner.next();
                System.out.print("Enter phone number(ex: 09350592591):");
                String phoneNumber = scanner.next();
                System.out.print("Enter username:");
                String in_username = scanner.next();
                System.out.print("Enter password:");
                String in_password = scanner.next();
                CustomerRegister newCustomer = new CustomerRegister(nationalCode,firstName,lastName,phoneNumber,in_username,in_password);
                CustomerRepository customerRepository = new CustomerRepository();
                customerRepository.saveCustomer(newCustomer);
                System.out.println("success");
            }
        }
        else
            System.out.println("error");
    }
}