# koala

Use Spring Boot Like A Koala

## 项目目标

### 核心功能

- [x] 基础模型
- [x] 基础服务
- [x] MyBatis
- [x] Swagger3
- [x] OAuth2
- [ ] 系统管理接口
- [ ] CRUD低代码

### 启动类

- [x] [钉钉](https://github.com/Houtaroy/koala/tree/main/koala-components/koala-dingtalk-spring-boot-starter)
- [x] [Druid拓展](https://github.com/Houtaroy/koala/tree/main/koala-components/koala-druid-spring-boot-starter)
- [x] [Mqtt](https://github.com/Houtaroy/koala/tree/main/koala-components/koala-integration-mqtt-spring-boot-starter)
- [x] [Office](https://github.com/Houtaroy/koala/tree/main/koala-components/koala-office-spring-boot-starter)
- [x] [OAuth2](https://github.com/Houtaroy/koala/tree/main/koala-components/koala-oauth2-authorization-server-spring-boot-starter)
- [x] [敏感词过滤](https://github.com/Houtaroy/koala/tree/main/koala-components/koala-sensitive-word-spring-boot-starter)
- [x] [Web](https://github.com/Houtaroy/koala/tree/main/koala-components/koala-web-spring-boot-starter)
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

### 引入依赖管理

```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>cn.koala</groupId>
      <artifactId>koala-dependencies</artifactId>
      <version>2022.0.0-SNAPSHOT</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>
```

### 引入依赖

```xml
<dependencies>
  <dependency>
    <groupId>cn.koala</groupId>
    <artifactId>koala-druid-spring-boot-starter</artifactId>
  </dependency>
</dependencies>
```

