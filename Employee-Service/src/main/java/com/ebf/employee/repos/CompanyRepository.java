package com.ebf.employee.repos;

import com.ebf.employee.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("select count(e.id) from Employee e where e.company = :companyId")
    long getEmployeeCountByCompany(@Param("companyId") long companyId);

    @Query("select avg(e.salary) from Employee e where e.company.id = :companyId")
    double getAverageSalaryForCompany(@Param("companyId") long companyId);
}
