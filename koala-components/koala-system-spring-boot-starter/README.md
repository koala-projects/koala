# 系统管理组件

实现了部分通用中后台管理功能, 包含: 字典管理 / 部门管理 / 角色管理 / 用户管理 / 个人服务

## 引入依赖

```xml

<dependencies>
  <dependency>
    <groupId>cn.koala</groupId>
    <artifactId>koala-system-spring-boot-starter</artifactId>
    <version>2022.0.0-SNAPSHOT</version>
  </dependency>
</dependencies>
```

## 初始化数据库

当前仅支持MySQL数据库, 推荐版本为8.0.X

使用脚本[schema-mysql.sql](https://github.com/koala-projects/koala/blob/main/koala-domains/koala-system/src/main/resources/schema-mysql.sql)
初始化数据库结构

使用脚本[data-mysql.sql](https://github.com/koala-projects/koala/blob/main/koala-domains/koala-system/src/main/resources/data-mysql.sql)
增加演示数据

## 接口文档

组件集成了`springdoc-openapi-ui`, 通过请求`swagger-ui/index.html`访问接口文档