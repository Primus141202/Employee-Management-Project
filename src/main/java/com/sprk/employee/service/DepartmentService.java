package com.sprk.employee.service;

import com.sprk.employee.dto.DepartmentRequest;
import com.sprk.employee.dto.DepartmentResponse;
import com.sprk.employee.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department getOrCreate(String code, String nameIfCreate);

    Department getByCodeOrThrow(String code);

    DepartmentResponse create(DepartmentRequest request);

    List<DepartmentResponse> getAll();

    DepartmentResponse getById(Long id);

    DepartmentResponse update(Long id, DepartmentRequest request);

    void delete(Long id);
}
