package carsharing.data.dao;

import carsharing.data.entities.Car;
import carsharing.data.entities.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDaoInterface {
    List<Customer> getAllCustomers() throws SQLException;
    Customer getCustomer(long id);
    void createCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(Customer customer);
}
