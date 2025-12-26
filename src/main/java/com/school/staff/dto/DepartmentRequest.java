package com.school.staff.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Department Request DTO
 */
@Data
public class DepartmentRequest {
    
    @NotBlank(message = "Department code is required")
    private String code;
    
    @NotBlank(message = "Department name is required")
    private String name;
    
    private String description;
    
    private Long parentId;
    
    private Integer level = 1;
    
    private Boolean isActive = true;
}
