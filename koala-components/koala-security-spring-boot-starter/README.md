# koala-security-spring-boot-starter

基于Spring Security 5, 提供OAuth2密码模式授权

## 引入依赖

```xml
<dependencies>
  <dependency>
    <groupId>cn.houtaroy.koala</groupId>
    <artifactId>koala-security-spring-boot-starter</artifactId>
    <version>2022.0.0-SNAPSHOT</version>
  </dependency>
</dependencies>
```

## 权限注解

```java
@RequestMapping("/users")
public interface UserApi {
    @PreAuthorize("hasAuthority('api:users:page')")
    DataResponse<Page<User>> page(@RequestParam Map<String, Object> parameters, Pageable pageable);
}
```

## 密码模式登录

```http
POST http://127.0.0.1:9999/oauth2/token
Content-Type: application/x-www-form-urlencoded

grant_type=password&username=admin&password=123456&client_id=vue&client_secret=123456
```

