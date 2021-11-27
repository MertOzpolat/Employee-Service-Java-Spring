package com.ebf.employee;

import com.ebf.employee.controllers.CompanyController;
import com.ebf.employee.controllers.EmployeeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmployeeDemoApplicationTests {


	@Autowired
	private CompanyController companyController;

	@Autowired
	private EmployeeController employeeController;

	@Test
	void contextLoads() {
		assertThat(companyController).isNotNull().describedAs("CompanyController is not null");
		assertThat(employeeController).isNotNull().describedAs("EmployeeController is not null");
	}

}
