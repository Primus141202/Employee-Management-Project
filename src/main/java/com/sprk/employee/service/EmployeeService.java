package com.sprk.employee.service;

import com.sprk.employee.dto.EmployeeRequest;
import com.sprk.employee.dto.EmployeeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    EmployeeResponse create(EmployeeRequest request);

    EmployeeResponse getById(Integer id);

    EmployeeResponse update(Integer id, EmployeeRequest request);

    void delete(Integer id);

    Page<EmployeeResponse> getAll(String department, Pageable pageable);

    Page<EmployeeResponse> list(Integer page, Integer size, String sortBy, String direction,
                                String gender, String departmentCode);
}
