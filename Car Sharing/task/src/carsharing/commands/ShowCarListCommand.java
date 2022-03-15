package carsharing.commands;

import carsharing.common.MenuStateHolder;
import carsharing.data.dao.CarDao;
import carsharing.data.dao.CompanyDao;
import carsharing.data.entities.Car;
import carsharing.data.entities.Company;

import java.util.List;

public class ShowCarListCommand implements Command {

    private Company company;

    public ShowCarListCommand(Company company) {
        this.company = company;
    }

    @Override
    public void run() {
        CarDao carDao = new CarDao();
        List<Car> cars = carDao.getAllCarsByCompanyId(company.getId());

        if (cars.isEmpty()) {
            System.out.println("\nThe car list is empty!");
        } else {
            System.out.println("\nCar list:");
            for (int i = 0; i < cars.size(); i++) {
                Car car = cars.get(i);
                System.out.printf(
                    "%s. %s%n",
                    i + 1,
                    car.getName()
                );
            }
        }
    }
}
