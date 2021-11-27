package com.ebf.employee.controllers;

import com.ebf.employee.controllers.exceptions.EmployeeNotFoundException;
import com.ebf.employee.entities.Employee;
import com.ebf.employee.entities.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTests {
    @Autowired
    private CompanyController companyController;
    @Autowired
    private EmployeeController employeeController;

    @Test
    public void addAndGetEmployees() {
        Company company = createTestCompany();
        Employee employee = createEmployeeForCompany(company);

        Page<Employee> employees = employeeController.getEmployees(Pageable.unpaged(), Optional.empty());

        assertThat(employees.getContent())
                .describedAs("getEmployees gets the newly added employee")
                .contains(employee);
    }

    @Test
    public void updateEmployee() {

        Company company = createTestCompany();
        Employee employee = createEmployeeForCompany(company);
        employee.setName("Mert");
        try {
            employeeController.updateEmployee(employee);
        } catch (EmployeeNotFoundException e) {
            e.printStackTrace();
        }
        Page<Employee> employees = employeeController.getEmployees(Pageable.unpaged(), Optional.empty());

        assertThat(employees)
                .contains(employee);
    }

    @Test
    public void deleteEmployee() {
        Company company = createTestCompany();
        Employee employee = createEmployeeForCompany(company);
        assertThat(employeeController.deleteEmployee(employee.getId())).isEqualTo(true);
    }

    public Company createTestCompany() {
        Company company = new Company();
        company.setName("EBF TEST");
        return companyController.addCompany(company);
    }

    public Employee createEmployeeForCompany(Company company) {
        Employee employee = new Employee();
        employee.setName("TESTNAME");
        employee.setSurname("TESTSURNAME");
        employee.setSalary(1000);
        employee.setEmail("test@test.com");
        employee.setCompany(company);
        return employeeController.addEmployee(employee);
    }
}
