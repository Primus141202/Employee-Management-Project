package com.sprk.employee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class EmployeeRequest {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    @NotBlank(message = "Gender is required")
    private String gender;

    @Min(value = 10000, message = "Salary must be>=10000")
    private double salary;

    @Email(message = "Invalid Email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Department Id is required")
    private long departmentID;
}
