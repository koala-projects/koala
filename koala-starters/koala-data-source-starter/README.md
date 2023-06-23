# Koala Data Source Starter

考拉数据源启动模块, 支持动态切换多数据源

## 快速开始

### 配置

```yaml
# 数据库连接池配置:
spring:
  datasource:
    hikari:
      minimum-idle: 5
      maximum-pool-size: 20
      connection-test-query: SELECT 1

# 数据源配置
koala:
  datasource:
    dynamic:
      - name: db1
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://127.0.0.1:3306/db1?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true
        username: db1
        password: 123456
      - name: db2
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://127.0.0.1:3306/db2?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true
        username: db2
        password: 123456
```

默认数据源为配置项的第一个

### 切换数据源

模块支持两种切换数据源的方式:

1. 手动编码切换数据源:

```java
public class UserService {
  
  public List<User> load(Long id) {
    DynamicDataSource.KEY_HOLDER.set("db2");
    // 业务逻辑...
  }
}
```

2. 使用注解切换数据源:

```java
public class UserService {
  
  @DynamicDataSourceKey("db2")
  public List<User> load(Long id) {
    // 业务逻辑...
  }
}
```

## 进阶

### 动态数据源工厂

模块在设计时, 默认约定所有数据源统一使用 Spring 数据源连接池配置

故, 针对不同的数据源连接池配置, 可通过实现`DynamicDataSourceFactory`接口进行自定义:

```java
public class MyDynamicDataSourceFactory implements DynamicDataSourceFactory<MyDataSource> {
  
  public MyDataSource create(DataSourceProperties properties) {
    // 创建数据源逻辑...
  }
}
```

模块内置了如下动态数据源工厂:

- HikariDynamicDataSourceFactory: Hikari连接池数据源工厂
- TomcatDynamicDataSourceFactory: Tomcat连接池数据源工厂
