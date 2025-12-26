package com.school.staff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * School Staff Management System Main Application
 */
@SpringBootApplication
@EnableCaching
public class StaffManageApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(StaffManageApplication.class, args);
    }
}
