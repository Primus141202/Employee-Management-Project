package com.sprk.employee.mapper;

import com.sprk.employee.dto.DepartmentRequest;
import com.sprk.employee.dto.DepartmentResponse;
import com.sprk.employee.dto.EmployeeResponse;
import com.sprk.employee.entity.Department;
import com.sprk.employee.entity.Employee;

import java.util.stream.Collectors;

public class DepartmentMapper {
    public static Department toEntity(DepartmentRequest dto) {
        Department department = new Department();
        department.setName(dto.getName());
        department.setLocation(dto.getLocation());
        return department;
    }

    // Entity -> Response
    public static DepartmentResponse toResponse(Department department) {
        DepartmentResponse dto = new DepartmentResponse();
        dto.setId(department.getId());
        dto.setName(department.getName());

        if (department.getEmployees() != null) {
            dto.setEmployees(
                    department.getEmployees().stream()
                            .map(DepartmentMapper::mapEmployeeToResponse)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    // Helper: Employee -> EmployeeResponse
    private static EmployeeResponse mapEmployeeToResponse(Employee emp) {
        EmployeeResponse empDto = new EmployeeResponse();
        empDto.setId(emp.getId());
        empDto.setFirstName(emp.getFirstName());
        empDto.setLastName(emp.getLastName());
        empDto.setEmail(emp.getEmail());
        empDto.setSalary(emp.getSalary());
        return empDto;
    }

    // Existing utility
    public static String toName(Department dept) {
        return dept != null ? dept.getName() : null;
    }
}
