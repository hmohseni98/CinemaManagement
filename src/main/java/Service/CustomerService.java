package Service;

import Entity.CustomerRegister;
import Repository.CustomerRepository;

import java.sql.SQLException;

public class CustomerService {
    private CustomerRepository customerRepository = new CustomerRepository();

    public void saveCustomer(CustomerRegister customerRegister) throws SQLException {
        customerRepository.saveCustomer(customerRegister);
    }
    public int checkAccount (String username,String password) throws SQLException {
        return customerRepository.checkAccount(username, password);
    }
}
