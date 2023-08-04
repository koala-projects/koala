# Koala Authorization Server Starter

考拉认证授权服务启动模块

基于[Spring Authorization Server](https://github.com/spring-projects/spring-authorization-server), 实现认证授权功能

## 快速开始

### 初始化

可通过如下两种方式初始化数据库:

1. 配置开启模块初始化器:

```yaml
koala:
  persist:
    initializer:
      authorization-server: true
```

2. 执行[脚本目录](../../koala-domains/koala-security/src/main/resources/database/security)下的结构脚本 `schema.sql`

### 配置

```yaml
koala:
  security:
    authorization-server:
      jwk:
        key-id: koala
        # RSA秘钥键值
        public-key: "koala"
        private-key: "koala"
```

### 认证授权

认证授权功能基于[OAuth 2.1](https://oauth.net/2.1/)协议, 以授权码模式为例:

```http
POST http://127.0.0.1:9000/oauth2/token
Content-Type: application/x-www-form-urlencoded

grant_type=authorization_code&client_id=koala-wechat-mini-app&client_secret=123456&redirect_uri=http://127.0.0.1:3100&code=123456
```

提供了用户信息相关接口, 可查询接口文档

## 进阶

### 自定义登录页

1. 开启自定义登录页配置:

```yaml
koala:
  security:
    authorization-server:
      custom-login-page: true
```

2. 引入视图模板渲染依赖:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
  </dependency>
```

3. 编写自定义登录页`resources/templates/login.html`

### 注册客户端

可通过手动实现注册客户端登记器接口`RegisteredClientRegistrar`自动注册客户端:

```java
@Component
public class MyRegisteredClientRegistrar implements RegisteredClientRegistrar {
  
  @Override
  public RegisteredClient getRegisteredClient() {
    // 构建注册客户端逻辑...
  }
}
```

### Token定制器

模块使用 Json Web Token 作为令牌, 可通过实现令牌定制器接口`JwtOAuth2TokenCustomizer`对 token 进行定制:

```java
@Component
public class MyTokenCustomizer implements JwtOAuth2TokenCustomizer {

  @Override
  public void customize(JwtEncodingContext context) {
    // 定制逻辑...
  }
}
```

模块内置了如下定制器:

- `ClaimGrantTypeAccessTokenCustomizer`: 授权类型定制器, 在 token 中增加授权类型属性`grant_type`
- `UserAuthenticationAccessTokenCustomizer`: 用户认证定制器, 在token中增加部分用户属性信息

### 安全过滤连附加处理器

在认证授权服务中, 实现了认证授权安全过滤链和默认安全过滤链

同时提供了对应的附加处理器接口`AuthorizationServerSecurityFilterChainPostProcessor`和`ResourceServerSecurityFilterChainPostProcessor`

若需要对认证授权安全过滤链进行定制, 可手动实现`AuthorizationServerSecurityFilterChainPostProcessor`接口:

```java
@Component
public class MyAuthorizationServerPostProcessor implements AuthorizationServerSecurityFilterChainPostProcessor {
  
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

