package com.school.staff.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

/**
 * Staff Request DTO
 */
@Data
public class StaffRequest {
    
    @NotBlank(message = "Staff number is required")
    private String staffNo;
    
    @NotBlank(message = "Name is required")
    private String name;
    
    private String gender;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    
    private String phone;
    
    @Email(message = "Invalid email format")
    private String email;
    
    private String idCard;
    
    private Long departmentId;
    
    private String position;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate;
    
    private String status;
    
    private String address;
    
    private Boolean isActive = true;
}
