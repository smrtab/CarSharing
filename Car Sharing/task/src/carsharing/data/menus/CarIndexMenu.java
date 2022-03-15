package carsharing.data.menus;

import carsharing.commands.Command;
import carsharing.commands.CreateCarCommand;
import carsharing.commands.ShowCarListCommand;
import carsharing.common.CarSharingException;
import carsharing.data.dao.CarDao;
import carsharing.data.dao.CompanyDao;
import carsharing.data.entities.Company;

import java.sql.SQLException;

public class CarIndexMenu implements Menu {

    private Company company;

    public CarIndexMenu(Company company) {
        this.company = company;
    }

    @Override
    public boolean validateSelectedPoint(int point) {
        return point <= 2 && point >= 0;
    }

    @Override
    public Menu handleClick(int point) throws CarSharingException, SQLException {
        Menu selectedMenu;
        switch (point) {
            case 0:
                selectedMenu = new CompanyIndexMenu();
                break;
            case 1:
                Command showCarListCommand = new ShowCarListCommand(company);
                showCarListCommand.run();
                selectedMenu = this;
                break;
            case 2:
                Command createCarCommand = new CreateCarCommand(company);
                createCarCommand.run();
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
            "'" + company.getName() + "' menu:",
            "1. Car list",
            "2. Create a car",
            "0. Back"
        );
    }
}
