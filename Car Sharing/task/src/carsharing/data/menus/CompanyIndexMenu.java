package carsharing.data.menus;

import carsharing.commands.Command;
import carsharing.commands.CreateCompanyCommand;
import carsharing.commands.CreateCustomerCommand;
import carsharing.common.CarSharingException;
import carsharing.data.dao.CompanyDao;
import carsharing.data.dao.CustomerDao;
import carsharing.data.entities.Company;

import java.sql.SQLException;
import java.util.List;

public class CompanyIndexMenu implements Menu {

    @Override
    public boolean validateSelectedPoint(int point) {
        return point <= 2 && point >= 0;
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
                } else {
                    selectedMenu = new CompanyListMenu(companies);
                }
                break;
            case 2:
                Command command = new CreateCompanyCommand();
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
        return String.format("%s%n%s%n%s",
            "1. Company list",
            "2. Create a company",
            "0. Back"
        );
    }
}
