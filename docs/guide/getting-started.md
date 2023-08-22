# 快速开始

根据实际需求, 可分为如下两种使用方式:

- [原型构建](#原型构建): 利用原型快速生成一整套项目代码, 适合从零开始搭建的业务系统
- [组件集成](#组件集成): 使用考拉提供的多种组件, 快速增加项目功能, 适合正在编码中或已上线的业务系统

## 原型构建

1. 在Maven配置文件`setting.xml`中引入考拉仓库:

```xml
<settings>
    <profiles>
        <profile>
            <id>koala</id>
            <repositories>
                <repository>
                    <id>koala</id>
                    <name>Koala Maven Repository</name>
                    <url>https://raw.github.com/koala-projects/maven-repositories/snapshot/</url>
                    <snapshots>
                        <enabled>true</enabled>
                        <checksumPolicy>warn</checksumPolicy>
                    </snapshots>
                </repository>
            </repositories>
        </profile>
    </profiles>
    <activeProfiles>
        <activeProfile>koala</activeProfile>
    </activeProfiles>
</settings>
```

2. 选择合适的原型:

| 原型名称                                                     | 原型说明                                     |
| ------------------------------------------------------------ | -------------------------------------------- |
| [koala-web-application](../../koala-archetypes/koala-web-application) | Web应用通用原型, 内置系统管理/代码生成等功能 |

3. 使用Maven命令生成项目:

```bash
mvn archetype:generate ^
 -DarchetypeGroupId=cn.koala ^
 -DarchetypeArtifactId=koala-web-application ^
 -DarchetypeVersion=2023.1.0-SNAPSHOT -X ^
 -DgroupId=cn.houtaroy ^
 -DartifactId=test ^
 -Dversion=2023.0.0-SNAPSHOT ^
 -Dpackage=ch.houtaroy.test
```

| 参数名称              | 参数描述       | 参数示例               |
| --------------------- | -------------- | ---------------------- |
| -DarchetypeGroupId    | 原型groupId    | cn.koala               |
| -DarchetypeArtifactId | 原型artifactId | koala-web-application  |
| -DarchetypeVersion    | 原型版本       | 2023.1.0-SNAPSHOT      |
| -DgroupId             | 项目groupId    | cn.houtaroy            |
| -DartifactId          | 项目artifactId | test                   |
| -Dversion             | 项目版本       | 2023.0.0-SNAPSHOT      |
| -Dpackage             | 项目包名       | cn.houtaroy.test       |
| ...                   | 原型额外参数   | 额外参数请参照原型文档 |

## 组件集成

1. 增加考拉提供的Maven仓库和依赖清单, 修改`pom.xml`:

```xml
<repositories>
  <repository>
    <id>koala</id>
    <name>koala</name>
      <url>https://raw.github.com/koala-projects/maven-repositories/snapshot/</url>
      <!--<url>https://gitee.com/koala-projects/maven-repositories/raw/snapshot/</url>-->
  </repository>
</repositories>

<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>cn.koala</groupId>
      <artifactId>koala-dependencies</artifactId>
      <version>2023.0.1-SNAPSHOT</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>
```

2. 从[组件列表](#组件列表)中选取自己需要的组件:

   | 组件名称                                                     | 组件说明                                                     |
   | ------------------------------------------------------------ | ------------------------------------------------------------ |
   | [koala-admin-client-starter](../../koala-starters/koala-admin-client-starter) | 快速集成Spring Boot Admin Client                             |
   | [koala-admin-server-starter](../../koala-starters/koala-admin-server-starter) | 快速集成Spring Boot Admin Server, 提供高级通知方式           |
   | [koala-attachment-starter](../../koala-starters/koala-attachment-starter) | 提供了附件上传和下载功能                                     |
   | [koala-authorization-server-starter](../../koala-starters/koala-authorization-server-starter) | 快速集成 Spring Authorization Server, 提供 OAuth 2.1 认证授权服务 |
   | [koala-cache-starter](../../koala-starters/koala-cache-starter) | 快速集成Redis缓存, 抽象了缓存条件配置                        |
   | [koala-code-starter](../../koala-starters/koala-code-starter) | 提供了代码生成功能                                           |
   | [koala-data-source-starter](../../koala-starters/koala-data-source-starter) | 提供了动态切换多数据源的功能                                 |
   | [koala-database-starter](../../koala-starters/koala-database-starter) | 提供了数据库管理功能                                         |
   | [koala-log-starter](../../koala-starters/koala-log-starter)  | 提供了操作日志功能                                           |
   | [koala-minio-starter](../../koala-starters/koala-minio-starter) | 快速集成MinIO对象存储, 自动注入操作客户端                    |
   | [koala-mybatis-starter](../../koala-starters/koala-mybatis-starter) | 持久化功能的 MyBatis 版本实现                                |
   | [koala-ocr-starter](../../koala-starters/koala-ocr-starter)  | 快速集成OCR功能, 支持 图片 / PDF 内容识别                    |
   | [koala-persist-starter](../../koala-starters/koala-persist-starter) | 定义部分持久化功能, 如模型/仓库/服务/实体监听器等            |
   | [koala-post-office-starter](../../koala-starters/koala-post-office-starter) | 提供了 邮件 / 短信 / 企业微信 等消息发送功能                 |
   | [koala-powerjob-worker-starter](../../koala-starters/koala-powerjob-worker-starter) | 快速集成PowerJob Worker, 拓展Groovy任务处理器                |
   | [koala-query-starter](../../koala-starters/koala-query-starter) | 提供了自定义SQL语句查询统计数据的功能                        |
   | [koala-resource-server-starter](../../koala-starters/koala-resource-server-starter) | 提供资源服务和接口权限功能                                   |
   | [koala-rocketmq-starter](../../koala-starters/koala-rocketmq-starter) | 快速集成Apache RocketMQ, 提供 Spring Boot 3 适配             |
   | [koala-sensitive-word-starter](../../koala-starters/koala-sensitive-word-starter) | 提供了敏感词过滤功能, 支持 Jackson                           |
   | [koala-system-starter](../../koala-starters/koala-system-starter) | 提供了用户管理/角色管理/部门管理/字典管理功能                |
   | [koala-task-starter](../../koala-starters/koala-task-starter) | 快速集成 Spring Scheduling 功能, 实现基于数据库的任务配置    |
   | [koala-template-starter](../../koala-starters/koala-template-starter) | 提供了模板组管理/模板管理功能                                |
   | [koala-validation-starter](../../koala-starters/koala-validation-starter) | 提供了参数校验功能, 支持提示消息自定义                       |
   | [koala-web-starter](../../koala-starters/koala-web-starter)  | 抽象出通用返回模型, 增加全局异常拦截器                       |
   | [koala-wechat-mini-app-starter](../../koala-starters/koala-wechat-mini-app-starter) | 快速集成微信小程序功能, 实现微信用户与系统用户绑定           |

3. 引入组件依赖, 以系统管理为例, 修改`pom.xml`:

```xml
<dependencies>
  <dependency>
    <groupId>cn.koala</groupId>
    <artifactId>koala-system-starter</artifactId>
  </dependency>
</dependencies>
```

## 初始化数据库

自 `2023.1.2` 版本开始, 考拉默认使用 [Flyway](https://github.com/flyway/flyway) 作为数据库版本管理工具

1. 引入 Flyway 依赖:

```xml
<dependency>
  <groupId>org.flywaydb</groupId>
  <artifactId>flyway-core</artifactId>
  <version>9.21.1</version>
</dependency>
```

2. 引入 Flyway 数据库依赖:

```xml
<dependency>
  <groupId>org.flywaydb</groupId>
  <artifactId>flyway-mysql</artifactId>
  <version>9.21.1</version>
</dependency>
```

3. 在[数据库脚本目录](../../db/migration)下选择项目数据库类型的数据库脚本, 移入项目目录 `resources/db/migration` :

```
db
|--migration
|  |--mysql
|    |--V2023.1.2.1__create_table.sql
|    |--V2023.1.2.2__insert_default_data.sql
```

>  若不想使用 Flyway, 直接在数据库中执行对应的脚本文件即可
