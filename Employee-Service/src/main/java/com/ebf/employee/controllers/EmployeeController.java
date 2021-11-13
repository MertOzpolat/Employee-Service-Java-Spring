package com.ebf.employee.controllers;

import com.ebf.employee.entities.Company;
import com.ebf.employee.entities.Employee;
import com.ebf.employee.repos.CompanyRepository;
import com.ebf.employee.repos.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    CompanyRepository companyRepository;

    @GetMapping
    public Iterable<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{company_id}")
    public Iterable<Employee> getEmployeesByCompany(@PathVariable long company_id){
        return employeeRepository.findAllByCompanyId(company_id);
    }

    @PostMapping("/add")
    public Iterable<Employee> addCompany(@RequestBody Employee employee) {
        employeeRepository.save(employee);
        return employeeRepository.findAll();
    }

    @PostMapping("/delete")
    public Iterable<Employee> deleteCompany(@RequestBody Long id) {
        employeeRepository.deleteById(id);
        return employeeRepository.findAll();
    }
}
