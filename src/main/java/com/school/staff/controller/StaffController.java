package com.school.staff.controller;

import com.school.staff.dto.ApiResponse;
import com.school.staff.dto.StaffRequest;
import com.school.staff.entity.Staff;
import com.school.staff.service.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Staff Controller
 */
@RestController
@RequestMapping("/staff")
@RequiredArgsConstructor
@Slf4j
public class StaffController {
    
    private final StaffService staffService;
    
    /**
     * Get all staff
     */
    @GetMapping
    public ApiResponse<List<Staff>> getAllStaff() {
        log.info("GET /staff - Get all staff");
        List<Staff> staffList = staffService.getAllStaff();
        return ApiResponse.success(staffList);
    }
    
    /**
     * Get staff by ID
     */
    @GetMapping("/{id}")
    public ApiResponse<Staff> getStaffById(@PathVariable Long id) {
        log.info("GET /staff/{} - Get staff by ID", id);
        Staff staff = staffService.getStaffById(id);
        return ApiResponse.success(staff);
    }
    
    /**
     * Get staff by staff number
     */
    @GetMapping("/staffno/{staffNo}")
    public ApiResponse<Staff> getStaffByStaffNo(@PathVariable String staffNo) {
        log.info("GET /staff/staffno/{} - Get staff by staff number", staffNo);
        Staff staff = staffService.getStaffByStaffNo(staffNo);
        return ApiResponse.success(staff);
    }
    
    /**
     * Get staff by department
     */
    @GetMapping("/department/{departmentId}")
    public ApiResponse<List<Staff>> getStaffByDepartment(@PathVariable Long departmentId) {
        log.info("GET /staff/department/{} - Get staff by department", departmentId);
        List<Staff> staffList = staffService.getStaffByDepartment(departmentId);
        return ApiResponse.success(staffList);
    }
    
    /**
     * Get staff by status
     */
    @GetMapping("/status/{status}")
    public ApiResponse<List<Staff>> getStaffByStatus(@PathVariable String status) {
        log.info("GET /staff/status/{} - Get staff by status", status);
        List<Staff> staffList = staffService.getStaffByStatus(status);
        return ApiResponse.success(staffList);
    }
    
    /**
     * Get staff by position
     */
    @GetMapping("/position/{position}")
    public ApiResponse<List<Staff>> getStaffByPosition(@PathVariable String position) {
        log.info("GET /staff/position/{} - Get staff by position", position);
        List<Staff> staffList = staffService.getStaffByPosition(position);
        return ApiResponse.success(staffList);
    }
    
    /**
     * Search staff by name
     */
    @GetMapping("/search")
    public ApiResponse<List<Staff>> searchStaff(@RequestParam String name) {
        log.info("GET /staff/search?name={} - Search staff", name);
        List<Staff> staffList = staffService.searchStaffByName(name);
        return ApiResponse.success(staffList);
    }
    
    /**
     * Create new staff
     */
    @PostMapping
    public ApiResponse<Staff> createStaff(@Valid @RequestBody StaffRequest request) {
        log.info("POST /staff - Create new staff: {}", request.getName());
        Staff staff = staffService.createStaff(request);
        return ApiResponse.success("Staff created successfully", staff);
    }
    
    /**
     * Update staff
     */
    @PutMapping("/{id}")
    public ApiResponse<Staff> updateStaff(
            @PathVariable Long id,
            @Valid @RequestBody StaffRequest request) {
        log.info("PUT /staff/{} - Update staff", id);
        Staff staff = staffService.updateStaff(id, request);
        return ApiResponse.success("Staff updated successfully", staff);
    }
    
    /**
     * Delete staff
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteStaff(@PathVariable Long id) {
        log.info("DELETE /staff/{} - Delete staff", id);
        staffService.deleteStaff(id);
        return ApiResponse.success("Staff deleted successfully", null);
    }
}
