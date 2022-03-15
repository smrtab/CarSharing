package carsharing.commands;

import carsharing.data.dao.CarDao;
import carsharing.data.dao.CompanyDao;
import carsharing.data.dao.CustomerDao;
import carsharing.data.entities.Car;
import carsharing.data.entities.Company;
import carsharing.data.entities.Customer;

public class ReturnRentedCarCommand implements Command {

    private Customer customer;

    public ReturnRentedCarCommand(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void run() {

        if (customer.getRentedCarId() == 0) {
            System.out.println("\nYou didn't rent a car!");
        } else {
            CustomerDao customerDao = new CustomerDao();
            customer.setRentedCarId(0L);
            customerDao.updateCustomer(customer);

            System.out.println("\nYou've returned a rented car!");
        }
    }
}
