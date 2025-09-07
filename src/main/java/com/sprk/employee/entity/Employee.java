package com.sprk.employee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Employee {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)private Integer id;

    @Column(nullable=false, length=64)
}
