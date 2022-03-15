package carsharing.data.menus;

import carsharing.commands.Command;
import carsharing.commands.CreateCustomerCommand;
import carsharing.common.CarSharingException;
import carsharing.data.dao.CompanyDao;
import carsharing.data.dao.CustomerDao;
import carsharing.data.entities.Company;
import carsharing.data.entities.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainMenu implements Menu {

    @Override
    public boolean validateSelectedPoint(int point) {
        return point <= 3 && point >= 0;
    }

    @Override
    public Menu handleClick(int point) throws CarSharingException, SQLException {
        Menu selectedMenu;
        switch (point) {
            case 0:
                selectedMenu = new ExitMenu();
                break;
            case 1:
                selectedMenu = new CompanyIndexMenu();
                break;
            case 2:
                CustomerDao customerDao = new CustomerDao();
                List<Customer> customers = customerDao.getAllCustomers();

                if (customers.isEmpty()) {
                    System.out.println("\nThe customer list is empty!");
                    selectedMenu = this;
                } else {
                    selectedMenu = new CustomerListMenu(customers);
                }
                break;
            case 3:
                Command command = new CreateCustomerCommand();
                command.run();
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
            "1. Log in as a manager",
            "2. Log in as a customer",
            "3. Create a customer",
            "0. Exit"
        );
    }
}
