package carsharing.data.dao;

import carsharing.common.CarSharingDBManager;
import carsharing.data.entities.Car;
import carsharing.data.entities.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDao implements CarDaoInterface {

    private CarSharingDBManager manager;

    public CarDao() {
        manager = CarSharingDBManager.getInstance();
    }

    @Override
    public List<Car> getAllFreeCarsByCompanyId(long id) {

        List<Car> carList = new ArrayList<>();
        String sql = "SELECT car.* " +
                "FROM car " +
                "LEFT JOIN customer ON customer.rented_car_id = car.id " +
                "WHERE company_id = ? AND customer.id IS NULL " +
                "ORDER BY id ASC";
        try (Connection connection = manager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    carList.add(mapResult(resultSet));
                }
                resultSet.close();
                return carList;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Car> getAllCarsByCompanyId(long id) {

        List<Car> carList = new ArrayList<>();
        String sql = "SELECT * FROM car WHERE company_id = ? ORDER BY id ASC";
        try (Connection connection = manager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    carList.add(mapResult(resultSet));
                }
                resultSet.close();
                return carList;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Car getCar(long id) {
        String sql = "SELECT * FROM car WHERE id = ?";
        try (Connection connection = manager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                return mapResult(resultSet);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public void createCar(Car car) {
        String sql = "INSERT INTO car (company_id, name) VALUES (?, ?)";
        try (Connection connection = manager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setLong(1, car.getCompanyId());
                statement.setString(2, car.getName());
                statement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateCar(Car car) {
        String sql = "UPDATE car SET company_id = ?, name = ? WHERE id = ?";
        try (Connection connection = manager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setLong(1, car.getCompanyId());
                statement.setString(2, car.getName());
                statement.setLong(3, car.getId());
                statement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteCar(Car car) {
        String sql = "DELETE FROM car WHERE id = ?";
        try (Connection connection = manager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setLong(1, car.getId());
                statement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private Car mapResult(ResultSet result) throws SQLException {
        Car car = new Car();
        car.setId(result.getLong("id"));
        car.setCompanyId(result.getLong("company_id"));
        car.setName(result.getString("name"));
        return car;
    }
}
