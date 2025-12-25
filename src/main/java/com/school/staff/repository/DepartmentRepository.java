package com.school.staff.repository;

import com.school.staff.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Department Repository
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
    Optional<Department> findByCode(String code);
    
    List<Department> findByParentId(Long parentId);
    
    List<Department> findByIsActive(Boolean isActive);
    
    List<Department> findByNameContaining(String name);
}
