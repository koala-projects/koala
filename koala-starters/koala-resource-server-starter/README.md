# Koala Resource Server Starter

考拉资源服务启动模块, 提供资源服务和接口权限功能

## 快速开始

### 配置

```yaml
spring:
  security:
    oauth2:
      resourceserver:
        opaque-token:
          # 认证授权服务地址
          introspection-uri: http://127.0.0.1:9000/oauth2/introspect
          # 注册客户端信息
          client-id: koala-admin
          client-secret: 123456
koala:
  security:
    resource-server:
      # 许可路径列表
      permit-all-patterns:
        - /api/databases/**
        - /api/code/**
```

许可路径书写规则请参照[PathPattern](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/util/pattern/PathPattern.html)

### 接口权限

使用注解`@PreAuthorize`标注接口权限:

```java
@RequestMapping("/api/my")
@RestController
// OpenApi权限注解
@SecurityRequirement(name = "spring-security")
public interface MyApi {
  
  // 权限注解
  @PreAuthorize("hasAuthority('my:list')")
  @GetMapping
  DataResponse<List<My>> list(@RequestParam Map<String, Object> parameters);
}
```

## 进阶

### 权限提取器

模块默认使用内省令牌, 可通过实现权限提取器接口`AuthorityExtractor`自定义权限提取逻辑:

```java
@Component
public class MyAuthorityExtractor implements AuthorityExtractor {
  
  @Override
  public boolean support(OAuth2AuthenticatedPrincipal principal) {
    // 是否支持当前凭证...
  }
  
  @Override
  public Collection<GrantedAuthority> extract(OAuth2AuthenticatedPrincipal principal) {
    // 权限提取逻辑...
  }
}
```

模块内置了如下提取器:

- `UserAuthenticationAuthorityExtractor`: 用户认证权限提取器, 根据认证信息中的用户信息, 提取用户权限

### 安全过滤连附加处理器

可手动通过实现资源安全过滤链附加处理器接口`ResourceServerSecurityFilterChainPostProcessor`对资源服务进行定制:

```java
@Component
public class MyResourceServerSecurityFilterChainPostProcessor implements ResourceServerSecurityFilterChainPostProcessor {
  
  @Override
  public void postProcessBeforeBuild(HttpSecurity http) {
    // 构建安全过滤链之前的处理逻辑...
  }
    
  @Override
  public void postProcessAfterBuild(HttpSecurity http) {
    // 构建安全过滤链之后的处理逻辑...
  }
}
```

