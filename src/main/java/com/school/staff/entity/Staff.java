package com.school.staff.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Staff Entity - Represents school personnel (teachers, administrators, etc.)
 */
@Entity
@Table(name = "staff")
@Data
public class Staff implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true, length = 50)
    private String staffNo;
    
    @Column(nullable = false, length = 50)
    private String name;
    
    @Column(length = 10)
    private String gender;
    
    @Column(name = "birth_date")
    private LocalDate birthDate;
    
    @Column(length = 20)
    private String phone;
    
    @Column(length = 100)
    private String email;
    
    @Column(name = "id_card", length = 20)
    private String idCard;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;
    
    @Column(length = 50)
    private String position;
    
    @Column(name = "hire_date")
    private LocalDate hireDate;
    
    @Column(length = 20)
    private String status;
    
    @Column(length = 500)
    private String address;
    
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
