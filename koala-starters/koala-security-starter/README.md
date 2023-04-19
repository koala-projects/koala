# Koala Security Starter

考拉安全启动模块

基于[Spring Authorization Server](https://github.com/spring-projects/spring-authorization-server), 实现认证授权功能

## 快速开始

### 配置

书写资源服务内省令牌配置:

```yaml
spring:
  security:
    oauth2:
      resourceserver:
        opaque-token:
          introspection-uri: http://127.0.0.1:9000/oauth2/introspect
          client-id: koala-admin
          client-secret: 123456
```

### 认证授权

认证授权功能基于[OAuth 2.1](https://oauth.net/2.1/)协议, 以授权码模式为例:

```http
POST http://127.0.0.1:9000/oauth2/token
Content-Type: application/x-www-form-urlencoded

grant_type=authorization_code&client_id=koala-wechat-mini-app&client_secret=123456&redirect_uri=http://127.0.0.1:3100&code=123456
```

提供了用户信息相关接口, 可查询接口文档

### 接口权限

可使用注解对接口进行权限校验, 例如:

```java
public class UserApi {
  
  @PreAuthorize("hasAuthority('system:user:load')")
  public User load(String id);
}
```

如部分接口不需要进行权限校验, 可通过配置实现:

```yaml
koala:
  security:
    permit-all-patterns:
      - /swagger*/**
```

路径书写规则请参照[PathPattern](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/util/pattern/PathPattern.html)

## 进阶

### 后置处理器

对于认证授权服务和默认安全服务, 提供了对应的后置处理器接口`AuthorizationServerPostProcessor`与`DefaultSecurityPostProcessor`

例如, 需要对认证授权服务进行定制, 可手动实现`AuthorizationServerPostProcessor`接口:

```java
public class MyAuthorizationServerPostProcessor implements AuthorizationServerPostProcessor {
  @Override
  public void postProcessBeforeInitialization(HttpSecurity http) {
    // 构建SecurityFilterChain之前的处理逻辑...
  }
    
  @Override
  public void postProcessAfterInitialization(HttpSecurity http) {
    // 构建SecurityFilterChain之后的处理逻辑...
  }
}
```

### 注册客户端

模块会自动创建一个id为`koala-admin`的注册客户端

如果您需要对其进行定制, 可继承`AbstractRegisteredClientRegistry`抽象类:

```java
@Component("defaultRegisteredClientRegistry")
public class MyRegisteredClientRegistry extends AbstractRegisteredClientRegistry {
  
  @Override
  protected RegisteredClient obtainRegisteredClient() {
    // 定制注册客户端...
  }
}
```

### 审计员感知器

在实际业务中, 我们可能会需要获取当前登录的用户作为审计对象, 例如创建用户时

模块内置了一个安全审计员感知器`SpringSecurityAuditorAware`

如果此感知器无法满足需求, 可手动实现`AuditorAware`接口进行自定义:

```java
public class MyAuditorAware implements AuditorAware<MyUser> {
  
  @Override
  @NonNull
  public Optional<MyUser> getCurrentAuditor() {
    // 获取当前用户逻辑...
  }
}
```

