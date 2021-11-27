package com.ebf.employee.controllers;

import com.ebf.employee.controllers.exceptions.CompanyNotFoundException;
import com.ebf.employee.entities.Company;
import com.ebf.employee.entities.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyControllerTests {
    @Autowired
    private CompanyController companyController;
    @Autowired
    private EmployeeController employeeController;

    @Test
    public void addAndGetCompanies() {
        Company company = createTestCompany();

        Page<Company> companies = companyController.getCompanies(Pageable.unpaged());

        assertThat(companies)
                .describedAs("getCompanies gets the newly added company")
                .contains(company);
    }

    @Test
    public void updateCompany() {
        Company company = createTestCompany();
        company.setName("EBF TSET");
        try {
            companyController.updateCompany(company);
        } catch (CompanyNotFoundException e) {
            e.printStackTrace();
        }
        Page<Company> companies = companyController.getCompanies(Pageable.unpaged());

        assertThat(companies)
                .contains(company);
    }

    @Test
    public void deleteCompany() {
        Company company = createTestCompany();
        assertThat(companyController.deleteCompany(company.getId())).isEqualTo(true);
    }

    @Test
    public void getAverageSalary() {

        Company company = createTestCompany();
        createEmployeeForCompany(company);
        try {
            double res = companyController.getAverageSalary(1);
            assertThat(res).isEqualTo(1000);
        } catch (CompanyNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Company createTestCompany() {
        Company company = new Company();
        company.setName("EBF TEST");
        return companyController.addCompany(company);
    }

    public void createEmployeeForCompany(Company company) {
        Employee employee = new Employee();
        employee.setName("TESTNAME");
        employee.setSurname("TESTSURNAME");
        employee.setSalary(1000);
        employee.setEmail("test@test.com");
        employee.setCompany(company);
        employeeController.addEmployee(employee);
    }
}
