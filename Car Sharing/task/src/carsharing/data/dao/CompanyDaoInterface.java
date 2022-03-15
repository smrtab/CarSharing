package carsharing.data.dao;

import carsharing.data.entities.Company;

import java.sql.SQLException;
import java.util.List;

public interface CompanyDaoInterface {
    List<Company> getAllCompanies() throws SQLException;
    Company getCompany(long id);
    void createCompany(Company company);
    void updateCompany(Company company);
    void deleteCompany(Company company);
}