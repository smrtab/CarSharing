package carsharing.data.menus;

import carsharing.common.CarSharingException;
import carsharing.data.dao.CompanyDao;
import carsharing.data.dao.CustomerDao;
import carsharing.data.entities.Company;
import carsharing.data.entities.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerListMenu implements Menu {

    private List<Customer> customers = new ArrayList<>();

    public CustomerListMenu(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public boolean validateSelectedPoint(int point) {
        return point <= customers.size() && point >= 0;
    }

    @Override
    public Menu handleClick(int point) throws CarSharingException {

        if (point == 0) {
            return new MainMenu();
        }

        Customer customer = customers.get(point - 1);
        return new RentIndexMenu(customer);
    }

    @Override
    public String toString() {
        String output = "Choose a customer:\n";
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            output += String.format("%s. %s%n",
                i + 1,
                customer.getName()
            );
        }
        output += "0. Back";
        return output;
    }
}
