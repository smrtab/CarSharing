package carsharing.commands;

import carsharing.common.MenuStateHolder;
import carsharing.data.dao.CarDao;
import carsharing.data.dao.CompanyDao;
import carsharing.data.entities.Car;
import carsharing.data.entities.Company;

import java.util.Scanner;

public class CreateCarCommand implements Command {

    private Company company;

    public CreateCarCommand(Company company) {
        this.company = company;
    }

    @Override
    public void run() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the car name:");
        String name = scanner.nextLine();

        Car car = new Car();
        car.setCompanyId(company.getId());
        car.setName(name);

        CarDao carDao = new CarDao();
        carDao.createCar(car);

        System.out.println("\nThe car was added!");
    }
}
