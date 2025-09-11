package com.sprk.employee.service.impl;

import com.sprk.employee.dto.EmployeeRequest;
import com.sprk.employee.dto.EmployeeResponse;
import com.sprk.employee.entity.Department;
import com.sprk.employee.entity.Employee;
import com.sprk.employee.exception.ResourceNotFoundException;
import com.sprk.employee.repository.EmployeeRepository;
import com.sprk.employee.service.DepartmentService;
import com.sprk.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.awt.print.Pageable;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    public EmployeeResponse create(EmployeeRequest request){
        if (employeeRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("Email already exists: "+request.getEmail());
        }
        Department dept=departmentService.getOrCreate(request.getDepartmentID(),request.getDepartmentID());
        Employee saved=employeeRepository.save(EmployeeMapper.toEntity(request, dept));
        return EmployeeMapper.toResponse(saved);
    }

    public EmployeeResponse getById(Integer id){
        Employee e=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee id"+id+"not found"));
        return EmployeeMapper.toResponse(e);
    }

    public EmployeeResponse update(Integer id, EmployeeRequest request){
        Employee e=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee id"+id+"not found"));

        Department dept = null;
        if (StringUtils.hasText(request.getDepartmentID())){
            dept=departmentService.getByCodeorThrow(request.getDepartmentID());
        }
        EmployeeMapper.copyNonNull(request, e, dept);
        // If email was changed, check uniqueness
        if (StringUtils.hasText(request.getEmail()) && employeeRepository.existsByEmail(request.getEmail())
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
    public Page<EmployeeResponse> list(Integer page, Integer size, String sortBy, String direction,
                                       String gender, String departmentCode) {
        int p = (page == null || page < 0) ? 0 : page;
        int s = (size == null || size <= 0) ? 10 : size;
        String sortField = StringUtils.hasText(sortBy) ? sortBy : "id";
        Sort sort = "desc".equalsIgnoreCase(direction) ? Sort.by(sortField).descending() : Sort.by(sortField).ascending();
        Pageable pageable = PageRequest.of(p, s, sort);

        Page<Employee> employees;

        if (StringUtils.hasText(gender) && StringUtils.hasText(departmentCode)) {
            employees = employeeRepository.findByGenderAndDepartment_Code(gender, departmentCode, pageable);
        } else if (StringUtils.hasText(gender)) {
            employees = employeeRepository.findByGender(gender, pageable);
        } else if (StringUtils.hasText(departmentCode)) {
            employees = employeeRepository.findByDepartment_Code(departmentCode, pageable);
        } else {
            employees = employeeRepository.findAll(pageable);
        }

        return employees.map(EmployeeMapper::toResponse);
    }
}


