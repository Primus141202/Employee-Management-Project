package com.sprk.employee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DepartmentRequest {
    @Schema(
            description = "Name of the department",
            example = "Human Resources",
            required = true
    )
    private String name;

    @Schema(
            description = "Location of the department",
            example = "Mumbai Office",
            required = false
    )
    private String location;
}
