# School Personnel Management System Backend

一个基于 Spring Boot 3.2、JDK 17、MySQL 和 Redis 的学校人员信息管理系统后端。

## 技术栈

- **JDK**: 17
- **Spring Boot**: 3.2.0
- **数据库**: MySQL 8.0+
- **缓存**: Redis
- **构建工具**: Maven
- **ORM**: Spring Data JPA (Hibernate)
- **其他**: Lombok, Validation

## 功能特性

### 核心功能

1. **员工管理 (Staff Management)**
   - 员工信息的增删改查
   - 按部门、职位、状态查询员工
   - 员工信息搜索
   - Redis 缓存支持

2. **部门管理 (Department Management)**
   - 部门信息的增删改查
   - 支持层级结构（父部门/子部门）
   - 部门信息搜索
   - Redis 缓存支持

### 技术特性

- RESTful API 设计
- 统一的响应格式
- 全局异常处理
- 数据验证
- CORS 跨域支持
- Redis 缓存优化
- 事务管理
- 日志记录

## 系统要求

- JDK 17 或更高版本
- Maven 3.6+
- MySQL 8.0+
- Redis 6.0+

## 快速开始

### 1. 克隆项目

```bash
git clone https://github.com/leikuman1/staffManageBack.git
cd staffManageBack
```

### 2. 配置数据库

创建 MySQL 数据库：

```sql
CREATE DATABASE staff_management CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 3. 配置 Redis

确保 Redis 服务正在运行：

```bash
redis-server
```

### 4. 修改配置文件

编辑 `src/main/resources/application.yml`，根据实际情况修改数据库和 Redis 连接信息：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/staff_management?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true
    username: root
    password: your_password
  
  data:
    redis:
      host: localhost
      port: 6379
      password: your_redis_password  # 如果有密码的话
```

### 5. 构建和运行

```bash
# 构建项目
mvn clean package

# 运行应用
mvn spring-boot:run
```

或者直接运行打包后的 jar 文件：

```bash
java -jar target/staff-manage-back-1.0.0.jar
```

应用将在 `http://localhost:8080` 启动。

## API 文档

### 基础路径

所有 API 的基础路径为：`http://localhost:8080/api`

### 员工管理 API

| 方法 | 路径 | 描述 |
|------|------|------|
| GET | /api/staff | 获取所有员工 |
| GET | /api/staff/{id} | 根据 ID 获取员工 |
| GET | /api/staff/staffno/{staffNo} | 根据工号获取员工 |
| GET | /api/staff/department/{departmentId} | 获取部门下的所有员工 |
| GET | /api/staff/status/{status} | 根据状态获取员工 |
| GET | /api/staff/position/{position} | 根据职位获取员工 |
| GET | /api/staff/search?name={name} | 搜索员工 |
| POST | /api/staff | 创建新员工 |
| PUT | /api/staff/{id} | 更新员工信息 |
| DELETE | /api/staff/{id} | 删除员工 |

### 部门管理 API

| 方法 | 路径 | 描述 |
|------|------|------|
| GET | /api/departments | 获取所有部门 |
| GET | /api/departments/{id} | 根据 ID 获取部门 |
| GET | /api/departments/code/{code} | 根据编码获取部门 |
| GET | /api/departments/parent/{parentId} | 获取子部门 |
| GET | /api/departments/search?name={name} | 搜索部门 |
| POST | /api/departments | 创建新部门 |
| PUT | /api/departments/{id} | 更新部门信息 |
| DELETE | /api/departments/{id} | 删除部门 |

### 请求示例

#### 创建员工

```bash
curl -X POST http://localhost:8080/api/staff \
  -H "Content-Type: application/json" \
  -d '{
    "staffNo": "T001",
    "name": "张三",
    "gender": "男",
    "birthDate": "1990-01-01",
    "phone": "13800138000",
    "email": "zhangsan@school.com",
    "idCard": "110101199001011234",
    "departmentId": 1,
    "position": "教师",
    "hireDate": "2020-09-01",
    "status": "在职",
    "address": "北京市朝阳区",
    "isActive": true
  }'
```

#### 创建部门

```bash
curl -X POST http://localhost:8080/api/departments \
  -H "Content-Type: application/json" \
  -d '{
    "code": "DEPT001",
    "name": "计算机学院",
    "description": "计算机科学与技术学院",
    "parentId": null,
    "level": 1,
    "isActive": true
  }'
```

### 响应格式

所有 API 返回统一的 JSON 格式：

```json
{
  "code": 200,
  "message": "Success",
  "data": {
    // 数据内容
  }
}
```

## 数据模型

### Staff (员工)

| 字段 | 类型 | 说明 |
|------|------|------|
| id | Long | 主键 |
| staffNo | String | 工号（唯一） |
| name | String | 姓名 |
| gender | String | 性别 |
| birthDate | LocalDate | 出生日期 |
| phone | String | 电话 |
| email | String | 邮箱 |
| idCard | String | 身份证号 |
| department | Department | 所属部门 |
| position | String | 职位 |
| hireDate | LocalDate | 入职日期 |
| status | String | 状态 |
| address | String | 地址 |
| isActive | Boolean | 是否启用 |
| createdAt | LocalDateTime | 创建时间 |
| updatedAt | LocalDateTime | 更新时间 |

### Department (部门)

| 字段 | 类型 | 说明 |
|------|------|------|
| id | Long | 主键 |
| code | String | 部门编码（唯一） |
| name | String | 部门名称 |
| description | String | 描述 |
| parentId | Long | 父部门 ID |
| level | Integer | 层级 |
| isActive | Boolean | 是否启用 |
| createdAt | LocalDateTime | 创建时间 |
| updatedAt | LocalDateTime | 更新时间 |

## 项目结构

```
src/main/java/com/school/staff/
├── StaffManageApplication.java    # 主应用类
├── config/                         # 配置类
│   ├── RedisConfig.java           # Redis 配置
│   └── WebConfig.java             # Web 配置（CORS）
├── controller/                     # 控制器层
│   ├── DepartmentController.java  # 部门控制器
│   └── StaffController.java       # 员工控制器
├── dto/                           # 数据传输对象
│   ├── ApiResponse.java           # 统一响应格式
│   ├── DepartmentRequest.java     # 部门请求 DTO
│   └── StaffRequest.java          # 员工请求 DTO
├── entity/                        # 实体类
│   ├── Department.java            # 部门实体
│   └── Staff.java                 # 员工实体
├── exception/                     # 异常处理
│   └── GlobalExceptionHandler.java # 全局异常处理器
├── repository/                    # 数据访问层
│   ├── DepartmentRepository.java  # 部门仓储
│   └── StaffRepository.java       # 员工仓储
└── service/                       # 业务逻辑层
    ├── DepartmentService.java     # 部门服务
    └── StaffService.java          # 员工服务
```

## 缓存策略

系统使用 Redis 缓存以下数据：

- 员工信息（缓存键：`staff::{id}`）
- 部门信息（缓存键：`departments::{id}`）
- 缓存过期时间：1 小时

## 开发建议

1. **环境变量配置**：生产环境建议使用环境变量配置敏感信息
2. **日志级别**：生产环境建议将日志级别调整为 INFO 或 WARN
3. **数据库索引**：根据查询模式添加适当的数据库索引
4. **API 安全**：建议添加认证授权机制（如 Spring Security + JWT）
5. **接口文档**：可集成 Swagger/OpenAPI 自动生成 API 文档

## 贡献

欢迎提交 Issue 和 Pull Request！

## 许可证

MIT License