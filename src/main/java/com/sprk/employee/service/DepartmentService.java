package com.sprk.employee.service;

import com.sprk.employee.entity.Department;

public interface DepartmentService {
    Department getOrCreate(String code, String nameIfCreate);
    Department getByCodeorThrow(String code);
    DepartmentResponse create(DepartmentRequest request);

    // Get a list of all departments as DTOs
    List<DepartmentResponse> getAll();

    // Get a single department by ID as DTO
    DepartmentResponse getById(Long id);

    // Optionally, if you want to add update and delete
    DepartmentResponse update(Long id, DepartmentRequest request);

    void delete(Long id);
}
