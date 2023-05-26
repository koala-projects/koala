# Koala Admin Server Starter

考拉Spring Boot Admin Server启动模块, 提供高级通知服务

## 快速开始

### 配置

```yaml
spring:
  data:
    mongodb:
      host: 127.0.0.1
      database: test
      auto-index-creation: true
```

Spring Boot Admin Server 相关配置参照[官方文档](https://docs.spring-boot-admin.com/current/getting-started.html)即可

### 接口文档

实现了应用管理/运维工程师管理/运维关系管理/通知策略管理接口, 可通过访问接口文档查看具体信息

接口文档地址: `http://127.0.0.1:8080/swagger-ui.html`

### 通知

在创建运维关系后, 可通过配置开启企业微信(wework)和邮箱通知(mail):

1. 引入相应依赖:

```xml
<dependencies>
    <!-- 邮箱 -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>
    <!-- 企业微信 -->
    <dependency>
      <groupId>com.github.binarywang</groupId>
      <artifactId>wx-java-cp-spring-boot-starter</artifactId>
    </dependency>
  </dependencies>
```

2. 书写配置:

```yaml
# 邮箱配置
spring:
  mail:
    host: smtp.yeah.net
    username: koala_projects@yeah.net
    password: 123456

# 企业微信配置
wx:
  cp:
    corp-id: corp-id
    corp-secret: corp-secret
    agent-id: 10001

# 备用通知配置
koala:
  admin:
    server:
      fallback:
        # 备用通知策略
        strategy: mail
        # 备用
        maintainer:
          email: 'koala_projects@yeah.net'
```

## 进阶

### 自定义通知策略

若企业微信或邮箱不满足您的需求, 可通过实现接口`NotifyStrategy`进行自定义:

```java
@Component
public class MyNotifyStrategy implements NotifyStrategy {
    
  @Override
  public String getName() {
    return "my-notify";
  }
    
  @Override
  public boolean notify(Maintainer maintainer, Instance instance, InstanceEvent event) {
    // 通知逻辑...
  }
}
```

模块会自动识别自定义通知策略, 通过配置运维关系中的`strategy`属性即可生效使用
