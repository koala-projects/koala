# 数据模型组件

实现了简易数据模型功能

## 概念

- 元数据(Metadata): 数据的描述
- 属性(Property): 单个数据项的描述
- 数据元(Data Element): 单个数据项
- 数据(Data): 数据内容

## 初始化数据库

当前仅支持MySQL数据库, 推荐版本为8.0.X

1. 执行[数据库结构脚本](../../koala-domains/koala-data-model/koala-data-model-core/src/main/resources/schema-mysql.sql)

## 接口文档

组件集成了`springdoc-openapi-ui`, 通过访问`swagger-ui/index.html`阅读接口文档