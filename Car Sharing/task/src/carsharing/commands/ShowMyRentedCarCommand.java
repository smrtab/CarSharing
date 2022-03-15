package carsharing.commands;

import carsharing.data.dao.CarDao;
import carsharing.data.dao.CompanyDao;
import carsharing.data.entities.Car;
import carsharing.data.entities.Company;
import carsharing.data.entities.Customer;

public class ShowMyRentedCarCommand implements Command {

    private Customer customer;

    public ShowMyRentedCarCommand(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void run() {

        if (customer.getRentedCarId() == 0) {
            System.out.println("\nYou didn't rent a car!");
        } else {
            CarDao carDao = new CarDao();
            CompanyDao companyDao = new CompanyDao();

            Car car = carDao.getCar(customer.getRentedCarId());
            Company company = companyDao.getCompany(car.getCompanyId());

            System.out.printf(
                "Your rented car:%n%s%nCompany:%n%s%n",
                car.getName(),
                company.getName()
            );
        }
    }
}
