package com.school.staff.repository;

import com.school.staff.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Staff Repository
 */
@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    
    Optional<Staff> findByStaffNo(String staffNo);
    
    List<Staff> findByDepartmentId(Long departmentId);
    
    List<Staff> findByStatus(String status);
    
    List<Staff> findByIsActive(Boolean isActive);
    
    List<Staff> findByNameContaining(String name);
    
    List<Staff> findByPosition(String position);
}
