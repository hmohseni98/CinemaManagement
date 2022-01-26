package Service;

import Entity.AdminRegister;
import Repository.AdminRepository;

import java.sql.SQLException;

public class AdminService {
    private AdminRepository adminRepository = new AdminRepository();

    public void saveAdmin(AdminRegister adminRegister) throws SQLException {
        adminRepository.saveAdmin(adminRegister);
    }
    public int checkAccount(String username,String password) throws SQLException {
        return adminRepository.checkAccount(username,password);
    }

}
