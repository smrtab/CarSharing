package carsharing.commands;

import carsharing.data.dao.CustomerDao;
import carsharing.data.entities.Car;
import carsharing.data.entities.Company;
import carsharing.data.entities.Customer;

public class RentCarCommand implements Command {

    private Customer customer;

    private Car car;

    public RentCarCommand(
        Customer customer,
        Car car
    ) {
        this.customer = customer;
        this.car = car;
    }

    @Override
    public void run() {
        CustomerDao customerDao = new CustomerDao();
        customer.setRentedCarId(car.getId());
        customerDao.updateCustomer(customer);

        System.out.printf("\nYou rented '%s'%n", car.getName());
    }
}
