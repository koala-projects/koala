# 数据模型组件

简易的数据模型功能, 自动装配了基于MyBatis的实现, 提供REST接口

## 概念

- 元数据(Metadata): 数据的描述
- 属性(Property): 单个数据项的描述
- 数据源(Data Element): 单个数据项
- 数据(Data): 数据内容

## 引入依赖

```xml
<dependencies>
  <dependency>
    <groupId>cn.koala</groupId>
    <artifactId>koala-data-model-spring-boot-starter</artifactId>
    <version>2022.0.0-SNAPSHOT</version>
  </dependency>
</dependencies>
```

## 初始化数据库

当前仅支持MySQL数据库, 推荐版本为8.0.X

使用脚本`koala-data-model-core/schema-mysql.sql`初始化数据库

## 接口文档

组件集成了`springdoc-openapi-ui`, 通过请求`swagger-ui/index.html`访问接口文档