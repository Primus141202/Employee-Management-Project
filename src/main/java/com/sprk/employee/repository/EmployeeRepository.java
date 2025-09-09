package com.sprk.employee.repository;

import com.sprk.employee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    boolean existsByEmail(String email);

    //Filtering with pagination
    Page<Employee>
    findByGender(String gender, Pageable pageable);
    Page<Employee>
    findByDepartment_Code(String departmentCode, Pageable pageable);



}
