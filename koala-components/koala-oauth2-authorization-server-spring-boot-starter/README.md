# 考拉授权服务

基于Spring Security 5, 提供OAuth2密码模式授权

## 引入依赖

```xml

<dependencies>
  <dependency>
    <groupId>cn.koala</groupId>
    <artifactId>koala-oauth2-authorization-server-spring-boot-starter</artifactId>
    <version>2022.0.0-SNAPSHOT</version>
  </dependency>
</dependencies>
```

## 配置

```yaml
koala:
  security:
    # 默认为http://127.0.0.1:9999
    issuer: http://服务地址:服务端口
    jwt:
      token:
        # 默认为koala
        # 生产环境手动设置keyID
        keyID: my-system
```

## 实现UserDetailsService

```java

@Configuration
public class BeanConfig {

  @Bean
  public UserDetailsService userDetailsService() {
    return MyUserDetailsService();
  }
}
```

## 权限注解

```java

@RequestMapping("/users")
@RestController
@SecurityRequirement(name = "spring-security")
public interface UserApi {
  @PreAuthorize("hasAuthority('users:read')")
  DataResponse<Page<User>> page(@RequestParam Map<String, Object> parameters, Pageable pageable);
}
```

## 密码模式登录

```http
POST http://127.0.0.1:9999/oauth2/token
Content-Type: application/x-www-form-urlencoded

grant_type=password&username=admin&password=123456&client_id=vue&client_secret=123456
```

