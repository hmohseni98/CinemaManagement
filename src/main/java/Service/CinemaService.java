package Service;

import Entity.CinemaRegister;
import Repository.CinemaRepository;

import java.sql.SQLException;

public class CinemaService {
    private CinemaRepository cinemaRepository = new CinemaRepository();

    public void saveCinema(CinemaRegister cinemaRegister) throws SQLException {
        cinemaRepository.saveCinema(cinemaRegister);
    }
    public int checkAccount(String username,String password) throws SQLException {
        return cinemaRepository.checkAccount(username, password);
    }
    public void listOfCinema() throws SQLException {
        cinemaRepository.listOfCinema();
    }
    public int authenticate(int id) throws SQLException {
        return cinemaRepository.authenticate(id);
    }
    public int checkAuthenticationStatus(String username) throws SQLException {
        return cinemaRepository.checkAuthenticationStatus(username);
    }
    public int getIdByUsername(String username) throws SQLException {
        return cinemaRepository.getIdByUsername(username);
    }
    public String getNameByID(int id) throws SQLException{
        return cinemaRepository.getNameByID(id);
    }
}
