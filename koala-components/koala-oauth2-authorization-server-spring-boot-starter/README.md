# 授权服务组件

基于Spring Authorization Server实现OAuth2授权服务, 拓展了密码模式

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

