package com.ebf.employee.repos;

import com.ebf.employee.entities.Company;
import org.springframework.data.repository.CrudRepository;
import com.ebf.employee.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Iterable<Employee> findAllByCompanyId(long companyId);
}