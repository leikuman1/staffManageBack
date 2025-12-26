package com.school.staff.service;

import com.school.staff.dto.DepartmentRequest;
import com.school.staff.entity.Department;
import com.school.staff.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Department Service
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentService {
    
    private final DepartmentRepository departmentRepository;
    
    /**
     * Get all departments
     */
    public List<Department> getAllDepartments() {
        log.debug("Getting all departments");
        return departmentRepository.findAll();
    }
    
    /**
     * Get department by ID with caching
     */
    @Cacheable(value = "departments", key = "#id")
    public Department getDepartmentById(Long id) {
        log.debug("Getting department by id: {}", id);
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
    }
    
    /**
     * Get department by code
     */
    public Department getDepartmentByCode(String code) {
        log.debug("Getting department by code: {}", code);
        return departmentRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Department not found with code: " + code));
    }
    
    /**
     * Get departments by parent ID
     */
    public List<Department> getDepartmentsByParentId(Long parentId) {
        log.debug("Getting departments by parent id: {}", parentId);
        return departmentRepository.findByParentId(parentId);
    }
    
    /**
     * Search departments by name
     */
    public List<Department> searchDepartmentsByName(String name) {
        log.debug("Searching departments by name: {}", name);
        return departmentRepository.findByNameContaining(name);
    }
    
    /**
     * Create new department
     */
    @Transactional
    public Department createDepartment(DepartmentRequest request) {
        log.debug("Creating new department: {}", request.getName());
        
        // Check if code already exists
        if (departmentRepository.findByCode(request.getCode()).isPresent()) {
            throw new RuntimeException("Department code already exists: " + request.getCode());
        }
        
        Department department = new Department();
        department.setCode(request.getCode());
        department.setName(request.getName());
        department.setDescription(request.getDescription());
        department.setParentId(request.getParentId());
        department.setLevel(request.getLevel());
        department.setIsActive(request.getIsActive());
        
        return departmentRepository.save(department);
    }
    
    /**
     * Update department
     */
    @Transactional
    @CachePut(value = "departments", key = "#id")
    public Department updateDepartment(Long id, DepartmentRequest request) {
        log.debug("Updating department: {}", id);
        
        Department department = getDepartmentById(id);
        
        // Check if new code conflicts with existing department
        if (!department.getCode().equals(request.getCode())) {
            departmentRepository.findByCode(request.getCode()).ifPresent(d -> {
                if (!d.getId().equals(id)) {
                    throw new RuntimeException("Department code already exists: " + request.getCode());
                }
            });
        }
        
        department.setCode(request.getCode());
        department.setName(request.getName());
        department.setDescription(request.getDescription());
        department.setParentId(request.getParentId());
        department.setLevel(request.getLevel());
        department.setIsActive(request.getIsActive());
        
        return departmentRepository.save(department);
    }
    
    /**
     * Delete department
     */
    @Transactional
    @CacheEvict(value = "departments", key = "#id")
    public void deleteDepartment(Long id) {
        log.debug("Deleting department: {}", id);
        Department department = getDepartmentById(id);
        departmentRepository.delete(department);
    }
}
