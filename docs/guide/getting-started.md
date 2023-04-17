# 快速开始

根据实际需求, 可分为如下两种使用方式:

- [原型构建](#原型构建): 利用原型快速生成一整套项目代码, 适合从零开始搭建的业务系统
- [组件集成](#组件集成): 使用考拉提供的多种组件, 快速增加项目功能, 适合正在编码中或已上线的业务系统

## 原型构建

准备中, 敬请期待...

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

2. 从[组件列表](#组件列表)中选取自己需要的组件
3. 引入组件依赖, 以系统管理为例, 修改`pom.xml`:

```xml
<dependencies>
  <dependency>
    <groupId>cn.koala</groupId>
    <artifactId>koala-system-starter</artifactId>
  </dependency>
</dependencies>
```

### 组件列表

| 组件名称                                                     | 组件说明                                          |
| ------------------------------------------------------------ | ------------------------------------------------- |
| [koala-code-starter](../../koala-starters/koala-code-starter) | 提供了代码生成功能                                |
| [koala-database-starter](../../koala-starters/koala-database-starter) | 提供了数据库管理功能                              |
| [koala-log-starter](../../koala-starters/koala-log-starter)  | 提供了操作日志功能                                |
| [koala-mybatis-starter](../../koala-starters/koala-mybatis-starter) | 持久化功能的 MyBatis 版本实现                     |
| [koala-persist-starter](../../koala-starters/koala-persist-starter) | 定义部分持久化功能, 如模型/仓库/服务/实体监听器等 |
| [koala-security-starter](../../koala-starters/koala-security-starter) | 实现了基于OAuth2.1的认证授权体系                  |
| [koala-sensitive-word-starter](../../koala-starters/koala-sensitive-word-starter) | 提供了敏感词过滤功能, 支持Jackson                 |
| [koala-system-starter](../../koala-starters/koala-system-starter) | 提供了用户管理/角色管理/部门管理/字典管理功能     |
| [koala-template-starter](../../koala-starters/koala-template-starter) | 提供了模板组管理/模板管理功能                     |
| [koala-validation-starter](../../koala-starters/koala-validation-starter) | 提供了参数校验功能, 支持提示消息自定义            |
| [koala-web-starter](../../koala-starters/koala-web-starter)  | 抽象出通用返回模型, 增加全局异常拦截器            |

