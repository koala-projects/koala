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

| 组件名称                                                     | 组件说明                                                     |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [koala-log-starter](../../koala-starters/koala-log-starter)  | 提供了操作日志功能                                           |
| [koala-mybatis-starter](../../koala-starters/koala-mybatis-starter) | 抽象出部分通用的数据模型, 并在此基础上对增删改查进行简单封装 |
| [koala-web-starter](../../koala-starters/koala-web-starter)  | 抽象出通用返回模型, 增加全局异常拦截器                       |
| [koala-security-starter](../../koala-starters/koala-security-starter) | 实现了基于OAuth2.1的认证授权体系                             |
| [koala-system-starter](../../koala-starters/koala-system-starter) | 提供了用户管理/角色管理/部门管理/字典管理功能                |

