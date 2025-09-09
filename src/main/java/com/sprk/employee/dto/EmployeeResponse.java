package com.sprk.employee.dto;

import lombok.Data;

@Data
public class EmployeeResponse {

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
    private Double salary;
    private String email;
    private String departmentID;
}
