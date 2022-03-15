package carsharing.data.dao;

import carsharing.common.CarSharingDBManager;
import carsharing.data.entities.Company;
import carsharing.data.entities.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements CustomerDaoInterface {

    private CarSharingDBManager manager;

    public CustomerDao() {
        manager = CarSharingDBManager.getInstance();
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT * FROM customer ORDER BY id ASC";
        try (Connection connection = manager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)){
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    customerList.add(mapResult(resultSet));
                }
                resultSet.close();
                return customerList;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Customer getCustomer(long id) {

        String sql = "SELECT * FROM customer WHERE id = ?";
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
    public void createCustomer(Customer customer) {
        String sql = "INSERT INTO customer (name) VALUES (?)";
        try (Connection connection = manager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1, customer.getName());
                statement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        String sql = "UPDATE customer SET rented_car_id = ?, name = ? WHERE id = ?";
        try (Connection connection = manager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)){

                if (customer.getRentedCarId() == 0) {
                    statement.setNull(1, Types.INTEGER);
                } else {
                    statement.setLong(1, customer.getRentedCarId());
                }

                statement.setString(2, customer.getName());
                statement.setLong(3, customer.getId());
                statement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteCustomer(Customer customer) {
        String sql = "DELETE FROM customer WHERE id = ?";
        try (Connection connection = manager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setLong(1, customer.getId());
                statement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private Customer mapResult(ResultSet result) throws SQLException {
        Customer customer = new Customer();
        customer.setId(result.getLong("id"));
        customer.setRentedCarId(result.getLong("rented_car_id"));
        customer.setName(result.getString("name"));
        return customer;
    }
}
