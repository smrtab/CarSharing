package carsharing.data.menus;

import carsharing.commands.Command;
import carsharing.commands.RentCarCommand;
import carsharing.common.CarSharingException;
import carsharing.data.dao.CarDao;
import carsharing.data.dao.CompanyDao;
import carsharing.data.entities.Car;
import carsharing.data.entities.Company;
import carsharing.data.entities.Customer;

import java.sql.SQLException;
import java.util.List;

public class RentCarSelectMenu implements Menu {

    private Customer customer;
    private Company company;
    private List<Car> cars;

    RentCarSelectMenu(
        Customer customer,
        Company company,
        List<Car> cars
    ) {
        this.customer = customer;
        this.company = company;
        this.cars = cars;
    }

    @Override
    public boolean validateSelectedPoint(int point) {
        return point <= cars.size() && point >= 0;
    }

    @Override
    public Menu handleClick(int point) throws CarSharingException, SQLException {

        if (point == 0) {
            CompanyDao companyDao = new CompanyDao();
            List<Company> companies = companyDao.getAllCompanies();
            return new RentCompanySelectMenu(customer, companies);
        }

        Car car = cars.get(point - 1);

        Command rentCarCommand = new RentCarCommand(customer, car);
        rentCarCommand.run();

        return new RentIndexMenu(customer);
    }

    @Override
    public String toString() {
        String output = "Choose a car:\n";
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            output += String.format("%s. %s%n",
                i + 1,
                car.getName()
            );
        }
        output += "0. Back";
        return output;
    }
}
