# Koala 考拉 :koala:

[English Documentation](https://github.com/koala-projects/koala/tree/main/docs/en)

## 介绍

考拉(Koala)是一款免费开源的管理系统脚手架, 基于`Spring Boot`和`Vue`生态开发

项目提供了多种**自动装配组件**, 只需要引入依赖即可快速进行业务代码编写, 并会持续完善集成更多的配套设施

相较于其它同类产品, 考拉的不同之处在于:

- 考拉致力于成为程序员的得力**助手**, 而非**替代品**
- 考拉致力于成为便捷/易用/适合二次开发的**脚手架**
- 考拉将严格遵循国际通用标准, **绝不魔改或过度封装**
- 考拉不是**框架**或**平台**
- 代码要跑, **文档**先行

## 项目预览

- [考拉示例项目](https://koala.dxl.pink/): 每天定时重置数据

## 技术栈

### 后端

- Java: 17
- Spring Boot: 2.6.10
- MyBatis: 3.5.7
- Swagger: v3

### 前端

- TypeScript: ^4.6.3
- Vue: ^3.2.33
- vben-admin: 2.8.0

## 快速开始

根据实际需求, 可分为如下两种使用方式:

- [原型构建](#原型构建): 利用原型快速生成一整套项目代码, 适合从零开始搭建的业务系统
- [组件集成](#组件集成): 使用考拉提供的多种组件, 快速增加项目功能, 适合正在编码中或已上线的业务系统

### 原型构建

1. 选择合适的原型, 列表如下:

| 原型名称                                                     | 原型说明                                                     |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [Web项目原型](https://github.com/koala-projects/koala/tree/main/koala-archetypes/koala-web-archetype) | Web项目通用原型, 标准多模块文件结构, 内置 代码检查 / 授权服务 / 系统管理  等功能 |

2. 在Maven配置中增加第三方原型库, 修改`settings.xml`:

```xml
<profiles>
	<profile>
	  <id>koala</id>
	  <repositories>
        <repository>
          <id>koala-gitee</id>
          <name>Koala Maven Repository Gitee</name>
          <url>https://gitee.com/koala-projects/maven-repositories/raw/snapshot/</url>
          <snapshots>
            <enabled>true</enabled>
            <checksumPolicy>warn</checksumPolicy>
          </snapshots>
        </repository>
      </repositories>
	</profile>
</profiles>
```

3. 使用Maven命令生成项目:

```
mvn archetype:generate ^
 -DarchetypeGroupId=cn.koala ^
 -DarchetypeArtifactId=koala-web-archetype ^
 -DarchetypeVersion=2022.0.1-SNAPSHOT -X ^
 -DgroupId=cn.houtaroy ^
 -DartifactId=test ^
 -Dversion=2022.0.0-SNAPSHOT ^
 -Dpackage=ch.houtaroy.test
```

| 参数名称              | 参数描述       | 参数示例                   |
| --------------------- | -------------- | -------------------------- |
| -DarchetypeGroupId    | 原型groupId    | cn.koala                   |
| -DarchetypeArtifactId | 原型artifactId | koala-web-archetype        |
| -DgroupId             | 项目groupId    | cn.houtaroy                |
| -DartifactId          | 项目artifactId | test                       |
| -Dversion             | 项目版本       | 2022.0.0-SNAPSHOT          |
| -Dpackage             | 项目包名       | cn.houtaroy.test           |
| ...                   | 原型额外参数   | 额外参数请参照原型wen'dang |

4. 使用开发工具打开项目, 进行业务系统编码

### 组件集成

1. 选择需要的组件, 列表如下:

| 组件名称                                                     | 组件说明                                                     |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [Web基础组件](https://github.com/koala-projects/koala/tree/main/koala-components/koala-web-spring-boot-starter) | 对部分通用Web功能进行集成和拓展, 例如 OpenApi接口文档 / 通用返回值 / 异常拦截 等 |
| [授权服务组件](https://github.com/koala-projects/koala/tree/main/koala-components/koala-oauth2-authorization-server-spring-boot-starter) | 基于Spring Authorization Server实现OAuth2授权服务, 拓展了密码模式 |
| [资源服务组件](https://github.com/koala-projects/koala/tree/main/koala-components/koala-oauth2-resource-server-spring-boot-starter) | 配合授权服务组件使用                                         |
| [系统管理组件](https://github.com/koala-projects/koala/tree/main/koala-components/koala-system-spring-boot-starter) | 实现了部分通用中后台管理功能, 例如 字典管理 / 部门管理 / 用户管理 等 |
| [数据模型组件](https://github.com/koala-projects/koala/tree/main/koala-components/koala-data-model-spring-boot-starter) | 实现了简易数据模型功能, 例如 元数据管理 / 数据管理 等        |
| [设置组件](https://github.com/koala-projects/koala/tree/main/koala-components/koala-setting-spring-boot-starter) | 实现了简易设置功能, 适用于系统配置热更新等场景               |
| [办公组件](https://github.com/koala-projects/koala/tree/main/koala-components/koala-office-spring-boot-starter) | 提供了部分办公相关辅助工具, 例如 Excel导出 / PDF读取 等      |
| [MQTT组件](https://github.com/koala-projects/koala/tree/main/koala-components/koala-integration-mqtt-spring-boot-starter) | 通过简易配置即可快速接入MQTT消息队列                         |
| [钉钉组件](https://github.com/koala-projects/koala/tree/main/koala-components/koala-dingtalk-spring-boot-starter) | 整合了新旧两版钉钉SDK                                        |

**强烈建议您在使用组件前仔细阅读文档**

2. 增加考拉提供的Maven仓库和依赖清单, 修改`pom.xml`:

```xml
<repositories>
  <repository>
    <id>koala</id>
    <name>koala</name>
    <!--<url>https://raw.github.com/koala-projects/maven-repositories/snapshot/</url>-->
    <url>https://gitee.com/koala-projects/maven-repositories/raw/snapshot/</url>
  </repository>
</repositories>

<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>cn.koala</groupId>
      <artifactId>koala-dependencies</artifactId>
      <version>2022.0.1-SNAPSHOT</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>
```

3. 引入组件依赖, 以系统管理为例, 修改`pom.xml`:

```xml
<dependencies>
  <dependency>
    <groupId>cn.koala</groupId>
    <artifactId>koala-system-spring-boot-starter</artifactId>
  </dependency>
</dependencies>
```

## 联系方式

如您有任何意见建议, 可以发送邮件至`koala_projects@yeah.net`

## 许可

[MIT © Koala 2022](./LICENSE)
