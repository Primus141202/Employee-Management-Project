package com.sprk.employee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DepartmentRequest {

    @Schema(
            description = "Unique code of the department",
            example = "GAME_DEV",
            required = true
    )
    private String code;

    @Schema(
            description = "Name of the department",
            example = "Game Development",
            required = true
    )
    private String name;

    @Schema(
            description = "Location of the department",
            example = "Bangalore Office",
            required = false
    )
    private String location;
}
