# Koala Wechat Mini App Starter

考拉微信小程序启动模块

基于[WxJava](https://github.com/Wechat-Group/WxJava), 快速接入微信小程序功能

## 快速开始

本模块基于[安全模块](../koala-security-starter)/[系统模块](../koala-system-starter), 请先了解上述模块的使用方法

### 配置

配置微信小程序参数:

```yaml
wx:
  miniapp:
    appid: appid
    secret: secret
```

其余配置可参考[WxJava配置](https://github.com/Wechat-Group/WxJava/tree/develop/spring-boot-starters/wx-java-miniapp-spring-boot-starter)

### 自动注入

可自动注入`WxMaService`, 与微信小程序服务交互:

```java
@RequiredArgsConstructor
public class MyWxService {
  private final WxMaService wxMaService;
    
  public void login(String code) {
    try {
      WxMaJscode2SessionResult res = wxMaService.jsCode2SessionInfo(authentication.getCode());
      // 登录操作...
    } catch (WxErrorException e) {
      throw new IllegalStateException(e.getLocalizedMessage());
    }
  }
}
```

## 进阶

### OAuth2

实现了微信用户与系统用户绑定, 可通过如下方式获取令牌:

```http
POST http://127.0.0.1:9000/oauth2/token
Content-Type: application/x-www-form-urlencoded

grant_type=wechat-mini-app&client_id=koala-wechat-mini-app&client_secret=123456&code=123456
```

### 注册客户端

模块会自动创建一个id为`koala-wechat-mini-app`的注册客户端

如果您需要对其进行定制, 可继承`AbstractRegisteredClientRegistry`抽象类:

```java
@Component("wechatMiniAppClientRegistry")
public class MyWechatMiniAppClientRegistry extends AbstractRegisteredClientRegistry {
  
  @Override
  protected RegisteredClient obtainRegisteredClient() {
    // 定制注册客户端...
  }
}
```

### 自定义用户注册器

模块提供了用户注册器接口`WechatMiniAppUserRegistry`, 默认实现为`SimpleWechatMiniAppUserRegistry`

如在实际业务中需要自定义用户注册逻辑, 可手动实现接口:

```java
@Component
public class MyWechatMiniAppUserRegistry implements WechatMiniAppUserRegistry {
  @Override
  public void register(WechatMiniAppUser wechatMiniAppUser) {
    // 自定义注册逻辑...
  }
}
```



