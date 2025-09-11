package com.sprk.employee.service;

import com.sprk.employee.dto.EmployeeRequest;
import com.sprk.employee.dto.EmployeeResponse;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    EmployeeResponse create(EmployeeRequest request);
    EmployeeResponse getById(Integer id);
    EmployeeResponse update(Integer id,EmployeeRequest request);
    void delete(Integer id);

    //Optional filters
    Page<EmployeeResponse>list(Integer page, Integer size, String sortBy, String direction, String gender, String departmentId);
}
