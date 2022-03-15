package carsharing.commands;

import carsharing.common.MenuStateHolder;
import carsharing.data.dao.CompanyDao;
import carsharing.data.entities.Company;

import java.util.List;
import java.util.Scanner;

public class CreateCompanyCommand implements Command {

    @Override
    public void run() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the company name:");
        String name = scanner.nextLine();

        Company company = new Company();
        company.setName(name);

        CompanyDao companyDao = new CompanyDao();
        companyDao.createCompany(company);

        System.out.println("\nThe company was created!");
    }
}
