-- Create database
CREATE DATABASE IF NOT EXISTS staff_management CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE staff_management;

-- Note: Tables will be auto-created by Hibernate when the application starts
-- This script is for reference and manual database setup if needed

-- Sample Department Data
-- INSERT INTO departments (code, name, description, parent_id, level, is_active, created_at, updated_at)
-- VALUES 
--   ('DEPT001', '计算机学院', '计算机科学与技术学院', NULL, 1, TRUE, NOW(), NOW()),
--   ('DEPT002', '数学学院', '数学与统计学院', NULL, 1, TRUE, NOW(), NOW()),
--   ('DEPT003', '物理学院', '物理与天文学院', NULL, 1, TRUE, NOW(), NOW());

-- Sample Staff Data
-- INSERT INTO staff (staff_no, name, gender, birth_date, phone, email, id_card, department_id, position, hire_date, status, address, is_active, created_at, updated_at)
-- VALUES 
--   ('T001', '张三', '男', '1980-05-15', '13800138001', 'zhangsan@school.com', '110101198005151234', 1, '教授', '2010-09-01', '在职', '北京市朝阳区', TRUE, NOW(), NOW()),
--   ('T002', '李四', '女', '1985-08-20', '13800138002', 'lisi@school.com', '110101198508201234', 1, '副教授', '2015-09-01', '在职', '北京市海淀区', TRUE, NOW(), NOW());
