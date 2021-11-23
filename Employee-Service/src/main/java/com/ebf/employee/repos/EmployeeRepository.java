package com.ebf.employee.repos;

import com.ebf.employee.entities.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ebf.employee.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select count (e.id) from Employee e")
    long getEmployeeCount(Long id);
    @Query("select e from Employee e where e.company.id = :companyId")
    Page<Employee> findAllByCompanyId(Pageable pageable, @Param("companyId") long companyId);
}