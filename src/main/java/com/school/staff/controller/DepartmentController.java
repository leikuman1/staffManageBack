package com.school.staff.controller;

import com.school.staff.dto.ApiResponse;
import com.school.staff.dto.DepartmentRequest;
import com.school.staff.entity.Department;
import com.school.staff.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Department Controller
 */
@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
@Slf4j
public class DepartmentController {
    
    private final DepartmentService departmentService;
    
    /**
     * Get all departments
     */
    @GetMapping
    public ApiResponse<List<Department>> getAllDepartments() {
        log.info("GET /departments - Get all departments");
        List<Department> departments = departmentService.getAllDepartments();
        return ApiResponse.success(departments);
    }
    
    /**
     * Get department by ID
     */
    @GetMapping("/{id}")
    public ApiResponse<Department> getDepartmentById(@PathVariable Long id) {
        log.info("GET /departments/{} - Get department by ID", id);
        Department department = departmentService.getDepartmentById(id);
        return ApiResponse.success(department);
    }
    
    /**
     * Get department by code
     */
    @GetMapping("/code/{code}")
    public ApiResponse<Department> getDepartmentByCode(@PathVariable String code) {
        log.info("GET /departments/code/{} - Get department by code", code);
        Department department = departmentService.getDepartmentByCode(code);
        return ApiResponse.success(department);
    }
    
    /**
     * Get departments by parent ID
     */
    @GetMapping("/parent/{parentId}")
    public ApiResponse<List<Department>> getDepartmentsByParentId(@PathVariable Long parentId) {
        log.info("GET /departments/parent/{} - Get departments by parent ID", parentId);
        List<Department> departments = departmentService.getDepartmentsByParentId(parentId);
        return ApiResponse.success(departments);
    }
    
    /**
     * Search departments by name
     */
    @GetMapping("/search")
    public ApiResponse<List<Department>> searchDepartments(@RequestParam String name) {
        log.info("GET /departments/search?name={} - Search departments", name);
        List<Department> departments = departmentService.searchDepartmentsByName(name);
        return ApiResponse.success(departments);
    }
    
    /**
     * Create new department
     */
    @PostMapping
    public ApiResponse<Department> createDepartment(@Valid @RequestBody DepartmentRequest request) {
        log.info("POST /departments - Create new department: {}", request.getName());
        Department department = departmentService.createDepartment(request);
        return ApiResponse.success("Department created successfully", department);
    }
    
    /**
     * Update department
     */
    @PutMapping("/{id}")
    public ApiResponse<Department> updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentRequest request) {
        log.info("PUT /departments/{} - Update department", id);
        Department department = departmentService.updateDepartment(id, request);
        return ApiResponse.success("Department updated successfully", department);
    }
    
    /**
     * Delete department
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteDepartment(@PathVariable Long id) {
        log.info("DELETE /departments/{} - Delete department", id);
        departmentService.deleteDepartment(id);
        return ApiResponse.success("Department deleted successfully", null);
    }
}
