package com.sprk.employee.controller;


import com.sprk.employee.dto.DepartmentResponse;
import com.sprk.employee.entity.Department;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class DepartmentController {
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAll();
        public ResponseEntity<List<DepartmentResponse>> getAllDepartments() {
            List<DepartmentResponse> departments = departmentService.getAll();
            return ResponseEntity.ok(departments);
        }

        public ResponseEntity<List<Department>> getAllDepartments() {
            @ApiResponse(responseCode = "404", description = "Department not found")
        })
        @GetMapping("/{id}")
        public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
            Department department = departmentService.getById(id);
            public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable Long id) {
                DepartmentResponse department = departmentService.getById(id);
                return ResponseEntity.ok(department);
            }

            @Operation(summary = "Create a new department", description = "Adds a new department into the system")
            @ApiResponses({
                    @ApiResponse(responseCode = "200", description = "Department created successfully"),
                    @ApiResponse(responseCode = "201", description = "Department created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid department details")
            })
            @PostMapping
            public ResponseEntity<Department> createDepartment(@Valid @RequestBody Department department) {
                Department saved = departmentService.create(department);
                public ResponseEntity<DepartmentResponse> createDepartment(@Valid @RequestBody DepartmentRequest departmentRequest) {
                    DepartmentResponse saved = departmentService.create(departmentRequest);
                    return ResponseEntity.created(URI.create("/api/departments/" + saved.getId()))
                            .body(saved);
                }

                @Operation(summary = "Update department", description = "Updates an existing department by ID")
                @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Department updated successfully"),
                        @ApiResponse(responseCode = "404", description = "Department not found")
                })
                @PutMapping("/{id}")
                public ResponseEntity<DepartmentResponse> updateDepartment(
                        @PathVariable Long id,
                        @Valid @RequestBody DepartmentRequest departmentRequest) {
                    DepartmentResponse updated = departmentService.update(id, departmentRequest);
                    return ResponseEntity.ok(updated);
                }

                @Operation(summary = "Delete department", description = "Deletes a department by ID")
                @ApiResponses({
                        @ApiResponse(responseCode = "204", description = "Department deleted successfully"),
                        @ApiResponse(responseCode = "404", description = "Department not found")
                })
                @DeleteMapping("/{id}")
                public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
                    departmentService.delete(id);
                    return ResponseEntity.noContent().build();
                }
}
