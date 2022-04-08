# koala

Use Spring Boot Like A Koala

## 项目目标

### 核心功能

- [x] 基础模型
- [x] 基础服务
- [x] MyBatis
- [ ] Swagger3
- [ ] 系统管理接口
- [ ] CRUD低代码
- [ ] Authorization Server

### 启动类

- [ ] Authorization Server
- [x] [Mqtt](https://github.com/Houtaroy/koala/tree/main/koala-starters/koala-integration-mqtt-spring-boot-starter)
- [ ] 微信小程序
- [ ] 钉钉

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