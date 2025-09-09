package com.sprk.employee.service;

import com.sprk.employee.entity.Department;

public interface DepartmentService {
    Department getOrCreate(String code, String nameIfCreate);
    Department getByCodeorThrow(String code);
}
