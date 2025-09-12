package com.sprk.employee.service.impl;

import com.sprk.employee.dto.DepartmentRequest;
import com.sprk.employee.dto.DepartmentResponse;
import com.sprk.employee.entity.Department;
import com.sprk.employee.exception.ResourceNotFoundException;
import com.sprk.employee.mapper.DepartmentMapper;
import com.sprk.employee.repository.DepartmentRepository;
import com.sprk.employee.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public Department getOrCreate(String code, String nameIfCreate) {
        return departmentRepository.findByCode(code)
                .orElseGet(() -> {
                    Department d = new Department();
                    d.setCode(code);
                    d.setName(nameIfCreate != null ? nameIfCreate : code);
                    return (Department) departmentRepository.save(d);
                });
    }

    @Override
    public Department getByCodeOrThrow(String code) {
        return departmentRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Department with code '" + code + "' not found"));
    }

    @Override
    public DepartmentResponse create(DepartmentRequest request) {
        Department department = DepartmentMapper.toEntity(request);
        Department saved = (Department) departmentRepository.save(department);
        return DepartmentMapper.toResponse(saved);
    }

    @Override
    public List<DepartmentResponse> getAll() {
        List<Department> list = departmentRepository.findAll();
        return list.stream().map(DepartmentMapper::toResponse).toList();
    }

    @Override
    public DepartmentResponse getById(Long id) {
        Department dept = null;
        try {
            dept = (Department) departmentRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Department id " + id + " not found"));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return DepartmentMapper.toResponse(dept);
    }

    @Override
    public DepartmentResponse update(Long id, DepartmentRequest request) {
        Department dept = null;
        try {
            dept = (Department) departmentRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Department id " + id + " not found"));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        dept.setName(request.getName());
        dept.setLocation(request.getLocation());
        Department updated = (Department) departmentRepository.save(dept);
        return DepartmentMapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Department id " + id + " not found");
        }
        departmentRepository.deleteById(id);
    }
}
