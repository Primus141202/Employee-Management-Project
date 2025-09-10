package com.sprk.employee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Department {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)private Integer id;

    @Column(nullable = false,length = 32)
    private String code;

    @Column(nullable = false,length = 128)
    private String name;
}
