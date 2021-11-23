package com.ebf.employee.controllers;

import com.ebf.employee.controllers.exceptions.CompanyNotFoundException;
import com.ebf.employee.entities.Company;
import com.ebf.employee.repos.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    CompanyRepository companyRepository;
    @GetMapping
    public Page<Company> getCompanies(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable long id) throws CompanyNotFoundException {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            return company.get();
        }

        throw new CompanyNotFoundException("Company by id("+id+") not found");
    }

    @PutMapping
    public Company addCompany(@RequestBody Company company) {
        return companyRepository.saveAndFlush(company);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCompany(@PathVariable Long id) {
        if(companyRepository.findById(id).isPresent()){
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @PostMapping
    public Company updateCompany(@RequestBody Company company) throws CompanyNotFoundException{
        if(companyRepository.findById(company.getId()).isPresent()){
            return companyRepository.saveAndFlush(company);
        }

        throw new CompanyNotFoundException("Company by id("+company.getId()+") not found for update");
    }

    @GetMapping("/averageSalary/{id}")
    public double getAverageSalary(@PathVariable long id) throws CompanyNotFoundException{
        if(!companyRepository.findById(id).isPresent()){
            throw new CompanyNotFoundException("Company by id("+id+") not found for getting average salary");
        }
        return companyRepository.getAverageSalaryForCompany(id);
    }

    @GetMapping("/employeeCount/{id}")
    public double getEmployeeCountByCompanyId(@PathVariable long id) throws CompanyNotFoundException{
        if(!companyRepository.findById(id).isPresent()){
            throw new CompanyNotFoundException("Company by id("+id+") not found for getting employee count");
        }
        return companyRepository.getEmployeeCountByCompany(id);
    }
}
