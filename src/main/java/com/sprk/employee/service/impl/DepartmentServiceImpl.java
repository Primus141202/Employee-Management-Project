package com.sprk.employee.service.impl;

import com.sprk.employee.entity.Department;
import com.sprk.employee.exception.ResourceNotFoundException;
import com.sprk.employee.repository.DepartmentRepository;
import com.sprk.employee.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    @Override
    public Department getOrCreate(String code, String nameIfCreate){
        return departmentRepository.findByCode(code)
                .orElseGet(()->{
                    Department d=new Department();
                    d.setCode(code);
                    d.setName(nameIfCreate !=null ?nameIfCreate:code);
                    return departmentRepository.save(d);
                });
    }
    public Department getByCodeOrThrow(String code) {
        return departmentRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Department with code '" + code + "' not found"));
    }
    

}
