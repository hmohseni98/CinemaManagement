import CustomException.*;
import Database.FillDataBase;
import Entity.AdminRegister;
import Entity.CinemaRegister;
import Entity.CustomerRegister;
import Entity.Ticket;
import Service.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static AdminService adminService = new AdminService();
    private static CinemaService cinemaService = new CinemaService();
    private static CustomerService customerService = new CustomerService();
    private static ReserveService reserveService = new ReserveService();
    private static TicketService ticketService = new TicketService();
    private static int selectMenu;
    private static int cinemaId;
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";

    public static void main(String[] args) throws SQLException {
        FillDataBase fillDataBase = new FillDataBase();
        //fillDataBase.insertData(); //faghat bar aval ejra shavad baad comment shavad chon data tekrar mishavad va error primary key midahad.
        welcomeMenu();
    }

    public static void timeValid(String inputTime) {
        LocalTime.parse(inputTime);
    }
    public static void dateValid(String inputDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.parse(inputDate);
    }

    public static void welcomeMenu() throws SQLException {
        try {
            System.out.println("Please select one option");
            System.out.println("1.admin");
            System.out.println("2.cinema");
            System.out.println("3.customer");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter number:");
            selectMenu = scanner.nextInt();
            if (selectMenu < 4)
                accountMenu(selectMenu);
            else
                throw new InvalidInputOption();
        } catch (InputMismatchException e) {
            System.out.println(TEXT_RED + "Invalid character" + TEXT_RESET);
            welcomeMenu();
        } catch (InvalidInputOption e) {
            System.out.println(e.toString());
            welcomeMenu();
        }
    }

    public static void customerMenu() throws SQLException {
        try {
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
                ticketService.listOfTicket();
                customerMenu();
            } else if (number == 2) {
                System.out.print("Enter film name:");
                String filmName = scanner.next();
                System.out.print("Enter date(ex:2022-02-10):");
                String date = scanner.next();
                ticketService.searchByNameAndDate(filmName, date);
                customerMenu();
            } else if (number == 3) {
                System.out.print("Enter ticket id:");
                int ticket_id = scanner.nextInt();
                System.out.print("Enter number of ticket:");
                int count = scanner.nextInt();
                if (count <= ticketService.getCapacityById(ticket_id)) {
                    ticketService.updateCapacity(ticket_id, count);
                    System.out.println("success");
                    customerMenu();
                } else
                    throw new OutOfCapacity();
            } else if (number == 4) {
                welcomeMenu();
            } else
                throw new InvalidInputOption();
        } catch (InputMismatchException e) {
            System.out.println(TEXT_RED + "Invalid Character" + TEXT_RESET);
            customerMenu();
        } catch (OutOfCapacity e) {
            System.out.println(e.toString());
            customerMenu();
        } catch (InvalidInputOption e) {
            System.out.println(e.toString());
            customerMenu();
        }
    }

    public static void cinemaMenu(int cinemaId) throws SQLException {
        try {
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
                dateValid(date);
                System.out.print("Enter Start Time(ex:18:00):");
                String startAt = scanner.next();
                timeValid(startAt);
                System.out.print("Enter End Time(ex:20:00):");
                String endAt = scanner.next();
                timeValid(endAt);
                System.out.print("Enter Capacity:");
                int capacity = scanner.nextInt();
                System.out.print("Enter Price Ticket:");
                float price = scanner.nextFloat();
                Ticket newTicket = new Ticket(cinemaId, filmName, date, startAt, endAt, capacity, price);
                ticketService.saveTicket(newTicket);
                System.out.println("success");
                cinemaMenu(cinemaId);
            } else if (number == 2) {
                ticketService.searchTicketByCinema(cinemaId);
                cinemaMenu(cinemaId);
            } else if (number == 3) {
                System.out.print("Enter your ticket id for check:");
                int ticket_id = scanner.nextInt();
                String[] result;
                result = ticketService.getDateAndStartTimeById(ticket_id);
                LocalDate date_ticket = LocalDate.parse(result[0]);
                LocalTime time_ticket = LocalTime.parse(result[1]);
                if (LocalDate.now().isBefore(date_ticket)) {
                    if (LocalTime.now().isBefore(time_ticket)) {
                        ticketService.deleteRowById(ticket_id);
                        System.out.println("success");
                        cinemaMenu(cinemaId);
                    } else
                        throw new CanNotDeleteTicket();
                } else
                    throw new CanNotDeleteTicket();
            } else if (number == 4)
                welcomeMenu();
            else
                throw new InvalidInputOption();
        } catch (CanNotDeleteTicket e) {
            System.out.println(e.toString());
            cinemaMenu(cinemaId);
        } catch (InvalidInputOption e) {
            System.out.println(e.toString());
            cinemaMenu(cinemaId);
        } catch (InputMismatchException e) {
            System.out.println(TEXT_RED + "Invalid Character" + TEXT_RESET);
            cinemaMenu(cinemaId);
        } catch (SQLException e) {
            System.out.println(TEXT_RED + "record does not exist" + TEXT_RESET);
            welcomeMenu();
        } catch (ParseException e) {
            System.out.println(TEXT_RED + "date format is not valid" + TEXT_RESET);
            cinemaMenu(cinemaId);
        }catch (DateTimeParseException e){
            System.out.println(TEXT_RED + "time format is not valid" + TEXT_RESET);
            cinemaMenu(cinemaId);
        }
    }

    public static void adminMenu() throws SQLException {
        try {
            int number;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please select one option");
            System.out.println("1.show list of cinema:");
            System.out.println("2.authenticate cinema account:");
            System.out.println("3.exit");
            System.out.print("Enter number:");
            number = scanner.nextInt();
            if (number == 1) {
                cinemaService.listOfCinema();
                adminMenu();
            } else if (number == 2) {
                System.out.print("Enter cinema ID:");
                number = scanner.nextInt();
                if (cinemaService.authenticate(number) == 1) {
                    System.out.println("success");
                    adminMenu();
                } else
                    throw new NotAuthorizedAccount();
            } else if (number == 3)
                welcomeMenu();
            else
                throw new InvalidInputOption();
        } catch (InvalidInputOption e) {
            System.out.println(e.toString());
            adminMenu();
        } catch (NotAuthorizedAccount e) {
            System.out.println(e.toString());
            adminMenu();
        } catch (InputMismatchException e) {
            System.out.println(TEXT_RED + "Invalid Character" + TEXT_RESET);
            adminMenu();
        }
    }


    public static boolean checkValidation(String type, String username, String password) throws SQLException {
        if (type.equals("admin")) {
            return adminService.checkAccount(username, password) == 1;
        } else if (type.equals("cinema")) {
            return cinemaService.checkAccount(username, password) == 1;
        } else if (type.equals("customer")) {
            return customerService.checkAccount(username, password) == 1;
        }
        return false;
    }

    public static void accountMenu(int number) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        try {
            if (number == 1) { //admin
                System.out.println("Please select one option:");
                System.out.println("1.sign in (help!!! username:admin password:admin)");
                System.out.println("2.sign up");
                System.out.println("3.exit");
                System.out.print("Enter number:");
                int inputNumber = scanner.nextInt();
                String username;
                String password;
                if (inputNumber == 1) { // admin sign in
                    System.out.print("username:");
                    username = scanner.next();
                    System.out.print("password:");
                    password = scanner.next();
                    if (checkValidation("admin", username, password))
                        adminMenu();
                    else
                        throw new InvalidAccount();
                } else if (inputNumber == 2) { //admin sign up
                    System.out.print("Enter first name:");
                    String firstName = scanner.next();
                    System.out.print("Enter last name:");
                    String lastName = scanner.next();
                    System.out.print("Enter username:");
                    String in_username = scanner.next();
                    System.out.print("Enter password:");
                    String in_password = scanner.next();
                    AdminRegister newAdmin = new AdminRegister(firstName, lastName, in_username, in_password);
                    adminService.saveAdmin(newAdmin);
                    System.out.println("success");
                } else if (inputNumber == 3) {
                    welcomeMenu();
                } else
                    throw new InvalidInputOption();
            } else if (number == 2) { //cinema
                System.out.println("Please select one option:");
                System.out.println("1.sign in (help!!! username:koroush1400 password:123456)");
                System.out.println("2.sign up");
                System.out.println("3.exit");
                System.out.print("Enter number:");
                int inputNumber = scanner.nextInt();
                String username;
                String password;
                if (inputNumber == 1) { // cinema sign in
                    System.out.print("username:");
                    username = scanner.next();
                    System.out.print("password:");
                    password = scanner.next();
                    if (checkValidation("cinema", username, password)) {
                        if (cinemaService.checkAuthenticationStatus(username) == 1) {
                            cinemaId = cinemaService.getIdByUsername(username);
                            cinemaMenu(cinemaId);
                        } else
                            throw new AccountNotAuthorized();
                    } else
                        throw new InvalidAccount();
                } else if (inputNumber == 2) { //cinema sign up
                    System.out.print("Enter cinema name:");
                    String name = scanner.next();
                    System.out.print("Enter address:");
                    scanner.next();
                    String address = scanner.nextLine();
                    System.out.print("Enter username:");
                    String in_username = scanner.next();
                    System.out.print("Enter password:");
                    String in_password = scanner.next();
                    CinemaRegister newCinema = new CinemaRegister(name, address, in_username, in_password);
                    cinemaService.saveCinema(newCinema);
                    System.out.println("success");
                } else if (inputNumber == 3) {
                    welcomeMenu();
                } else
                    throw new InvalidInputOption();
            } else if (number == 3) { //customer
                System.out.println("Please select one option:");
                System.out.println("1.sign in (help!!! username:mohseni98 password:123456)");
                System.out.println("2.sign up");
                System.out.println("3.exit");
                System.out.print("Enter number:");
                int inputNumber = scanner.nextInt();
                String username;
                String password;
                if (inputNumber == 1) { // customer sign in
                    System.out.print("username:");
                    username = scanner.next();
                    System.out.print("password:");
                    password = scanner.next();
                    if (checkValidation("customer", username, password))
                        customerMenu();
                    else
                        throw new InvalidAccount();
                } else if (inputNumber == 2) { //customer sign up
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
                    CustomerRegister newCustomer = new CustomerRegister(nationalCode, firstName, lastName, phoneNumber, in_username, in_password);
                    customerService.saveCustomer(newCustomer);
                    System.out.println("success");
                } else if (inputNumber == 3) {
                    welcomeMenu();
                } else
                    throw new InvalidInputOption();
            } else
                throw new InvalidInputOption();
        } catch (InvalidAccount e) {
            System.out.println(e.toString());
            accountMenu(selectMenu);
        } catch (InvalidInputOption e) {
            System.out.println(e.toString());
            accountMenu(selectMenu);
        } catch (AccountNotAuthorized e) {
            System.out.println(e.toString());
        } catch (InputMismatchException e) {
            System.out.println(TEXT_RED + "Invalid Character" + TEXT_RESET);
            accountMenu(selectMenu);
        }
    }
}