package carsharing.commands;

import carsharing.data.dao.CompanyDao;
import carsharing.data.dao.CustomerDao;
import carsharing.data.entities.Company;
import carsharing.data.entities.Customer;

import java.util.Scanner;

public class CreateCustomerCommand implements Command {

    @Override
    public void run() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the customer name:");
        String name = scanner.nextLine();

        Customer customer = new Customer();
        customer.setName(name);
        customer.setRentedCarId(0L);

        CustomerDao customerDao = new CustomerDao();
        customerDao.createCustomer(customer);

        System.out.println("\nThe customer was added!");
    }
}
