# Koala Security Starter

考拉安全启动模块

## 用户信息

实现了`UserDetails`和`UserDetailsService`, 在`UserDetails`中增加了用户id和用户昵称属性, 同时提供了`UserinfoApi`用户信息接口

基于 RBAC 权限模型, 依据用户-角色-权限的关系查询权限代码, 用作接口权限校验

具体内容可参考相应的实现类源码

## 授权认证

基于Spring Authorization Server实现了 OAuth2.1 认证授权协议, 且相关数据采用持久化的保存方式

在启动前请使用[脚本](../../koala-domains/koala-security/src/main/resources/database/init.sql)初始化数据库, 已适配 MySQL 8

启动时会默认注册一个注册客户端, 可查看表`oauth2_registered_client`

认证流程与接口完全遵循标准规范, 不再赘述

## 接口权限

可使用注解对接口进行权限验证, 例如:

```java
@PreAuthorize("hasAuthority('system:user:page')")
```

需要用户信息中包含`system:user:page`

如接口无需权限校验, 可以通过增加如下配置实现:

```yaml
koala:
  security:
    permit-all-patterns:
      - /api/users
      - /swagger*/**
```

路径匹配模式请参照[PathPattern](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/util/pattern/PathPattern.html)

## 令牌长度

如 RBAC 的颗粒度极为细致, 可能会出现 token 长度过长的问题

需要在项目和Web服务器(如Nginx)进行相应的配置

