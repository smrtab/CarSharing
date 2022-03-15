package carsharing.data.menus;

import carsharing.common.CarSharingException;
import carsharing.data.dao.CarDao;
import carsharing.data.dao.CompanyDao;
import carsharing.data.entities.Car;
import carsharing.data.entities.Company;
import carsharing.data.entities.Customer;

import java.util.ArrayList;
import java.util.List;

public class CompanyListMenu implements Menu {

    private List<Company> companies = new ArrayList<>();

    public CompanyListMenu(List<Company> companies) {
        this.companies = companies;
    }

    @Override
    public boolean validateSelectedPoint(int point) {
        return point <= companies.size() && point >= 0;
    }

    @Override
    public Menu handleClick(int point) throws CarSharingException {

        if (point == 0) {
            return new CompanyIndexMenu();
        }

        Company company = companies.get(point - 1);
        return new CarIndexMenu(company);
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
