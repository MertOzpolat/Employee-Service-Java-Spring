package com.ebf.employee.repos;

import org.springframework.data.repository.CrudRepository;
import com.ebf.employee.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}