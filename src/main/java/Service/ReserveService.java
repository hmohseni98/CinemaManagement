package Service;

import Entity.Reserve;
import Repository.ReserveRepository;

import java.sql.SQLException;

public class ReserveService {
    private ReserveRepository reserveRepository = new ReserveRepository();

    public void saveReserve(Reserve reserve) throws SQLException {
        reserveRepository.saveReserve(reserve);
    }
}
