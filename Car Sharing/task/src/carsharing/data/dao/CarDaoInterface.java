package carsharing.data.dao;

import carsharing.data.entities.Car;
import carsharing.data.entities.Company;

import java.sql.SQLException;
import java.util.List;

public interface CarDaoInterface {
    List<Car> getAllFreeCarsByCompanyId(long id) throws SQLException;
    List<Car> getAllCarsByCompanyId(long id) throws SQLException;
    Car getCar(long id);
    void createCar(Car car);
    void updateCar(Car car);
    void deleteCar(Car car);
}
