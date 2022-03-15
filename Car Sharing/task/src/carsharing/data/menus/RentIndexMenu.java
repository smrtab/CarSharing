package carsharing.data.menus;

import carsharing.commands.Command;
import carsharing.commands.CreateCarCommand;
import carsharing.commands.ReturnRentedCarCommand;
import carsharing.commands.ShowMyRentedCarCommand;
import carsharing.common.CarSharingException;
import carsharing.data.dao.CompanyDao;
import carsharing.data.entities.Company;
import carsharing.data.entities.Customer;

import java.sql.SQLException;
import java.util.List;

public class RentIndexMenu implements Menu {

    private Customer customer;

    RentIndexMenu(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean validateSelectedPoint(int point) {
        return point <= 3 && point >= 0;
    }

    @Override
    public Menu handleClick(int point) throws CarSharingException, SQLException {
        Menu selectedMenu;
        switch (point) {
            case 0:
                selectedMenu = new MainMenu();
                break;
            case 1:
                CompanyDao companyDao = new CompanyDao();
                List<Company> companies = companyDao.getAllCompanies();
                if (companies.isEmpty()) {
                    System.out.println("\nThe company list is empty!");
                    selectedMenu = this;
                } else if (customer.getRentedCarId() != 0) {
                    System.out.println("\nYou've already rented a car!");
                    selectedMenu = this;
                } else {
                    selectedMenu = new RentCompanySelectMenu(customer, companies);
                }
                break;
            case 2:
                Command createCarCommand = new ReturnRentedCarCommand(customer);
                createCarCommand.run();
                selectedMenu = this;
                break;
            case 3:
                Command showMyRentedCar = new ShowMyRentedCarCommand(customer);
                showMyRentedCar.run();
                selectedMenu = this;
                break;
            default:
                throw new CarSharingException("Incorrect menu point has been selected");
        }

        return selectedMenu;
    }

    @Override
    public String toString() {
        return String.format("%s%n%s%n%s%n%s",
            "1. Rent a car",
            "2. Return a rented car",
            "3. My rented car",
            "0. Back"
        );
    }
}