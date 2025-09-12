package com.sprk.employee.service.impl;

import com.sprk.employee.dto.EmployeeRequest;
import com.sprk.employee.dto.EmployeeResponse;
import com.sprk.employee.entity.Department;
import com.sprk.employee.entity.Employee;
import com.sprk.employee.exception.ResourceNotFoundException;
import com.sprk.employee.mapper.EmployeeMapper;
import com.sprk.employee.repository.EmployeeRepository;
import com.sprk.employee.service.DepartmentService;
import com.sprk.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    @Override
    public EmployeeResponse create(EmployeeRequest request) {
        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + request.getEmail());
        }
        Department dept = departmentService.getOrCreate(request.getDepartmentID(), request.getDepartmentID());
        Employee saved = employeeRepository.save(EmployeeMapper.toEntity(request, dept));
        return EmployeeMapper.toResponse(saved);
    }

    @Override
    public EmployeeResponse getById(Integer id) {
        Employee e = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee id " + id + " not found"));
        return EmployeeMapper.toResponse(e);
    }

    @Override
    public EmployeeResponse update(Integer id, EmployeeRequest request) {
        Employee e = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee id " + id + " not found"));

        Department dept = null;
        if (StringUtils.hasText(request.getDepartmentID())) {
            dept = departmentService.getByCodeOrThrow(request.getDepartmentID());
        }
        EmployeeMapper.updateEntity(e, request, dept);

        if (StringUtils.hasText(request.getEmail())
                && employeeRepository.existsByEmail(request.getEmail())
                && !request.getEmail().equalsIgnoreCase(e.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + request.getEmail());
        }

        Employee updated = employeeRepository.save(e);
        return EmployeeMapper.toResponse(updated);
    }

    @Override
    public void delete(Integer id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee id " + id + " not found");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public Page<EmployeeResponse> getAll(String department, Pageable pageable) {
        Page<Employee> employees;
        if (StringUtils.hasText(department)) {
            employees = employeeRepository.findByDepartment_Code(department, pageable);
        } else {
            employees = employeeRepository.findAll(pageable);
        }
        return employees.map(EmployeeMapper::toResponse);
    }

    @Override
    public Page<EmployeeResponse> list(Integer page, Integer size, String sortBy, String direction, String gender, String departmentCode) {
        return null;
    }
}
