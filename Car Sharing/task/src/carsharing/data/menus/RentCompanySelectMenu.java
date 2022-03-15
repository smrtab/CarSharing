package carsharing.data.menus;

import carsharing.common.CarSharingException;
import carsharing.data.dao.CarDao;
import carsharing.data.dao.CompanyDao;
import carsharing.data.dao.CustomerDao;
import carsharing.data.entities.Car;
import carsharing.data.entities.Company;
import carsharing.data.entities.Customer;

import java.sql.SQLException;
import java.util.List;

public class RentCompanySelectMenu implements Menu {

    private Customer customer;
    private List<Company> companies;

    RentCompanySelectMenu(
        Customer customer,
        List<Company> companies
    ) {
        this.customer = customer;
        this.companies = companies;
    }

    @Override
    public boolean validateSelectedPoint(int point) {
        return point <= companies.size() && point >= 0;
    }

    @Override
    public Menu handleClick(int point) throws CarSharingException, SQLException {

        if (point == 0) {
            return new RentIndexMenu(customer);
        }

        Company company = companies.get(point - 1);

        CarDao carDao = new CarDao();
        List<Car> cars = carDao.getAllFreeCarsByCompanyId(company.getId());

        Menu menu;
        if (cars.isEmpty()) {
            System.out.printf("%nNo available cars in the '%s' company%n", company.getName());
            menu = this;
        } else {
            menu = new RentCarSelectMenu(customer, company, cars);
        }
        return menu;
    }

    @Override
    public String toString() {
        String output = "Choose a company:\n";
        for (int i = 0; i < companies.size(); i++) {
            Company company = companies.get(i);
            output += String.format("%s. %s%n",
                i + 1,
                company.getName()
            );
        }
        output += "0. Back";
        return output;
    }
}
