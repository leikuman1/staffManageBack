# 快速启动指南 (Quick Start Guide)

## 使用 Docker Compose 快速启动（推荐）

### 1. 启动数据库和缓存服务

```bash
# 启动 MySQL 和 Redis
docker-compose up -d

# 等待服务就绪（约 30 秒）
docker-compose ps
```

### 2. 运行应用

```bash
# 使用 Maven 运行
mvn spring-boot:run

# 或者先构建再运行
mvn clean package
java -jar target/staff-manage-back-1.0.0.jar
```

### 3. 测试 API

应用启动后访问：http://localhost:8080/api

#### 创建部门示例

```bash
curl -X POST http://localhost:8080/api/departments \
  -H "Content-Type: application/json" \
  -d '{
    "code": "CS",
    "name": "计算机科学学院",
    "description": "负责计算机相关专业的教学和研究",
    "level": 1,
    "isActive": true
  }'
```

#### 创建员工示例

```bash
curl -X POST http://localhost:8080/api/staff \
  -H "Content-Type: application/json" \
  -d '{
    "staffNo": "T2024001",
    "name": "张三",
    "gender": "男",
    "birthDate": "1985-06-15",
    "phone": "13800138000",
    "email": "zhangsan@school.edu.cn",
    "departmentId": 1,
    "position": "教授",
    "hireDate": "2020-09-01",
    "status": "在职",
    "isActive": true
  }'
```

#### 查询所有员工

```bash
curl http://localhost:8080/api/staff
```

#### 按部门查询员工

```bash
curl http://localhost:8080/api/staff/department/1
```

### 4. 停止服务

```bash
# 停止应用：Ctrl+C

# 停止数据库和缓存
docker-compose down
```

## 手动配置方式

### 前置要求

- JDK 17+
- Maven 3.6+
- MySQL 8.0+ (运行在 3306 端口)
- Redis 6.0+ (运行在 6379 端口)

### 步骤

1. **创建数据库**

```sql
CREATE DATABASE staff_management CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. **修改配置** (如需要)

编辑 `src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    username: your_username
    password: your_password
```

3. **运行应用**

```bash
mvn spring-boot:run
```

## 常见问题

### 端口被占用

如果 8080 端口被占用，可以修改 `application.yml` 中的 `server.port`:
```yaml
server:
  port: 8888
```

### 数据库连接失败

- 确认 MySQL 正在运行
- 检查用户名和密码是否正确
- 确认数据库已创建

### Redis 连接失败

- 确认 Redis 正在运行
- 如果 Redis 有密码，在 `application.yml` 中配置

## 开发模式

开发时建议修改日志级别以获得更详细的信息：

```yaml
logging:
  level:
    com.school.staff: DEBUG
```

## 下一步

查看完整的 [README.md](README.md) 了解：
- 完整的 API 文档
- 数据模型说明
- 项目结构
- 部署建议
