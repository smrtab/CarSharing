package carsharing.data.dao;

import carsharing.common.CarSharingDBManager;
import carsharing.data.entities.Car;
import carsharing.data.entities.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDao implements CompanyDaoInterface {

    private CarSharingDBManager manager;

    public CompanyDao() {
        manager = CarSharingDBManager.getInstance();
    }

    @Override
    public List<Company> getAllCompanies() {

        List<Company> companyList = new ArrayList<>();
        String sql = "SELECT * FROM company ORDER BY id ASC";
        try (Connection connection = manager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)){
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    companyList.add(mapResult(resultSet));
                }
                resultSet.close();
                return companyList;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Company getCompany(long id) {

        String sql = "SELECT * FROM company WHERE id = ?";
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
    public void createCompany(Company company) {

        String sql = "INSERT INTO company (name) VALUES (?)";
        try (Connection connection = manager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)){

                statement.setString(1, company.getName());
                statement.executeUpdate();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateCompany(Company company) {

        String sql = "UPDATE company SET name = ? WHERE id = ?";
        try (Connection connection = manager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)){

                statement.setString(1, company.getName());
                statement.setLong(2, company.getId());
                statement.executeUpdate();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteCompany(Company company) {
        String sql = "DELETE FROM company WHERE id = ?";
        try (Connection connection = manager.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)){

                statement.setLong(1, company.getId());
                statement.executeUpdate();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private Company mapResult(ResultSet result) throws SQLException {
        Company company = new Company();
        company.setId(result.getLong("id"));
        company.setName(result.getString("name"));
        return company;
    }
}
