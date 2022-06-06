# koala

Use Spring Boot Like A Koala

## 项目目标

### 核心功能

- [x] 基础模型
- [x] 基础服务
- [x] MyBatis
- [x] Swagger3
- [x] Spring Security
- [ ] 系统管理接口
- [ ] CRUD低代码

### 启动类

- [x] Spring Security(https://github.com/Houtaroy/koala/tree/main/koala-components/koala-security-spring-boot-starter)
- [x] [Druid拓展](https://github.com/Houtaroy/koala/tree/main/koala-components/koala-druid-spring-boot-starter)
- [x] [Mqtt](https://github.com/Houtaroy/koala/tree/main/koala-components/koala-integration-mqtt-spring-boot-starter)
- [x] [钉钉](https://github.com/Houtaroy/koala/tree/main/koala-components/koala-dingtalk-spring-boot-starter)
- [ ] 微信小程序

## 如何使用

### 增加仓库

```xml

<repositories>
  <repository>
    <id>koala</id>
    <name>koala</name>
    <url>https://raw.github.com/Houtaroy/maven-repositories/snapshot/</url>
  </repository>
</repositories>
```

访问Github不方便的可以使用码云:

```xml

<repository>
  <id>koala</id>
  <name>koala</name>
  <url>https://gitee.com/houtaroy/maven-repositories/raw/snapshot/</url>
</repository>
```

### 引入依赖

```xml

<dependencies>
  <dependency>
    <groupId>cn.houtaroy.koala</groupId>
    <artifactId>koala-integration-mqtt-spring-boot-starter</artifactId>
    <version>2022.0.0-SNAPSHOT</version>
  </dependency>
</dependencies>
```