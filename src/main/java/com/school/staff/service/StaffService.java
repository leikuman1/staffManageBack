package com.school.staff.service;

import com.school.staff.dto.StaffRequest;
import com.school.staff.entity.Department;
import com.school.staff.entity.Staff;
import com.school.staff.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Staff Service
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class StaffService {
    
    private final StaffRepository staffRepository;
    private final DepartmentService departmentService;
    
    /**
     * Get all staff members
     */
    public List<Staff> getAllStaff() {
        log.debug("Getting all staff");
        return staffRepository.findAll();
    }
    
    /**
     * Get staff by ID with caching
     */
    @Cacheable(value = "staff", key = "#id")
    public Staff getStaffById(Long id) {
        log.debug("Getting staff by id: {}", id);
        return staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + id));
    }
    
    /**
     * Get staff by staff number
     */
    public Staff getStaffByStaffNo(String staffNo) {
        log.debug("Getting staff by staff number: {}", staffNo);
        return staffRepository.findByStaffNo(staffNo)
                .orElseThrow(() -> new RuntimeException("Staff not found with staff number: " + staffNo));
    }
    
    /**
     * Get staff by department
     */
    public List<Staff> getStaffByDepartment(Long departmentId) {
        log.debug("Getting staff by department: {}", departmentId);
        return staffRepository.findByDepartmentId(departmentId);
    }
    
    /**
     * Get staff by status
     */
    public List<Staff> getStaffByStatus(String status) {
        log.debug("Getting staff by status: {}", status);
        return staffRepository.findByStatus(status);
    }
    
    /**
     * Search staff by name
     */
    public List<Staff> searchStaffByName(String name) {
        log.debug("Searching staff by name: {}", name);
        return staffRepository.findByNameContaining(name);
    }
    
    /**
     * Get staff by position
     */
    public List<Staff> getStaffByPosition(String position) {
        log.debug("Getting staff by position: {}", position);
        return staffRepository.findByPosition(position);
    }
    
    /**
     * Create new staff member
     */
    @Transactional
    public Staff createStaff(StaffRequest request) {
        log.debug("Creating new staff: {}", request.getName());
        
        // Check if staff number already exists
        if (staffRepository.findByStaffNo(request.getStaffNo()).isPresent()) {
            throw new RuntimeException("Staff number already exists: " + request.getStaffNo());
        }
        
        Staff staff = new Staff();
        updateStaffFromRequest(staff, request);
        
        return staffRepository.save(staff);
    }
    
    /**
     * Update staff member
     */
    @Transactional
    @CachePut(value = "staff", key = "#id")
    public Staff updateStaff(Long id, StaffRequest request) {
        log.debug("Updating staff: {}", id);
        
        Staff staff = getStaffById(id);
        
        // Check if new staff number conflicts with existing staff
        if (!staff.getStaffNo().equals(request.getStaffNo())) {
            staffRepository.findByStaffNo(request.getStaffNo()).ifPresent(s -> {
                if (!s.getId().equals(id)) {
                    throw new RuntimeException("Staff number already exists: " + request.getStaffNo());
                }
            });
        }
        
        updateStaffFromRequest(staff, request);
        
        return staffRepository.save(staff);
    }
    
    /**
     * Delete staff member
     */
    @Transactional
    @CacheEvict(value = "staff", key = "#id")
    public void deleteStaff(Long id) {
        log.debug("Deleting staff: {}", id);
        Staff staff = getStaffById(id);
        staffRepository.delete(staff);
    }
    
    /**
     * Helper method to update staff entity from request
     */
    private void updateStaffFromRequest(Staff staff, StaffRequest request) {
        staff.setStaffNo(request.getStaffNo());
        staff.setName(request.getName());
        staff.setGender(request.getGender());
        staff.setBirthDate(request.getBirthDate());
        staff.setPhone(request.getPhone());
        staff.setEmail(request.getEmail());
        staff.setIdCard(request.getIdCard());
        staff.setPosition(request.getPosition());
        staff.setHireDate(request.getHireDate());
        staff.setStatus(request.getStatus());
        staff.setAddress(request.getAddress());
        staff.setIsActive(request.getIsActive());
        
        // Set department if provided
        if (request.getDepartmentId() != null) {
            Department department = departmentService.getDepartmentById(request.getDepartmentId());
            staff.setDepartment(department);
        }
    }
}
