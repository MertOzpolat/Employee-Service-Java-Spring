package com.ebf.employee.controllers;

import com.ebf.employee.controllers.exceptions.EmployeeNotFoundException;
import com.ebf.employee.entities.Company;
import com.ebf.employee.entities.Employee;
import com.ebf.employee.repos.CompanyRepository;
import com.ebf.employee.repos.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CompanyRepository companyRepository;

    @GetMapping
    public Page<Employee> getEmployees(Pageable pageable, @RequestParam(name = "companyId", required = false) Optional<Long> companyId) {
        if (companyId.isPresent()) {
            return employeeRepository.findAllByCompanyId(pageable, companyId.get());
        }
        return employeeRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable long id) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        }

        throw new EmployeeNotFoundException("Employee by id("+id+") not found");
    }

    @PutMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }

    @DeleteMapping("/{id}")
    public boolean deleteEmployee(@PathVariable Long id) {
        if(employeeRepository.findById(id).isPresent()){
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @PostMapping
    public Employee updateEmployee(@RequestBody Employee employee) throws EmployeeNotFoundException{
        if(employeeRepository.findById(employee.getId()).isPresent()){
            return employeeRepository.saveAndFlush(employee);
        }

        throw new EmployeeNotFoundException("Employee by id("+employee.getId()+") not found for update");
    }
}
