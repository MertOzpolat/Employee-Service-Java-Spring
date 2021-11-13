package com.ebf.employee.controllers;

import com.ebf.employee.entities.Company;
import com.ebf.employee.entities.Employee;
import com.ebf.employee.repos.CompanyRepository;
import com.ebf.employee.repos.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public Iterable<Company> getCompanies() {
        return companyRepository.findAll();
    }

    @PostMapping("/add")
    public Iterable<Company> addCompany(@RequestBody Company company) {
        companyRepository.save(company);
        return companyRepository.findAll();
    }

    @PostMapping("/delete/{id}")
    public Iterable<Company> deleteCompany(@PathVariable Long id) {
        companyRepository.deleteById(id);
        return companyRepository.findAll();
    }
    @PostMapping("/averageSalary/{id}")
    public double getAverageSalary(@PathVariable int id){
        System.out.println(id);
        Iterable<Employee> employees = employeeRepository.findAllByCompanyId(id);
        if(employees.iterator().hasNext()){
            System.out.println("heey");
            int empCount =0;
            double totalSalary =0;
            for(Employee emp : employees){
                if(emp.getCompanyId() == id){
                    totalSalary+=emp.getSalary();
                    empCount++;
                }
            }
            return totalSalary/empCount;
        }
        return 0;

    }
}
