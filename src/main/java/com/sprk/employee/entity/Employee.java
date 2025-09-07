package com.sprk.employee.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class Employee {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)private Integer id;

    @Column(nullable=false, length=64)
    private String firstName;

    @Column(nullable = false,length = 64)
    private String lastName;

    private int age;

    @Column(nullable = false,length = 10)
    private String gender;

    private double salary;

    @Column(nullable = false)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)

    @JoinColumn(name="department_id")
    private Department department;
}
