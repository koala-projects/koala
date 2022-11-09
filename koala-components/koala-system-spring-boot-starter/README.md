# 系统管理组件

实现了部分通用中后台管理功能

## 初始化数据库

当前仅支持MySQL数据库, 推荐版本为8.0.X

1. 执行[数据库结构脚本](../../koala-domains/koala-system/src/main/resources/schema-mysql.sql)
2. 执行[数据库数据脚本](../../koala-domains/koala-system/src/main/resources/data-mysql.sql)

## 配置

```yaml
koala:
  system:
    default-password: "koala-projects"
```

| 配置代码                        | 配置名称       | 配置类型 | 配置默认值       |
| ------------------------------- | -------------- | -------- | ---------------- |
| `koala.system.default-password` | 新用户默认密码 | 字符串   | "koala-projects" |

## 接口文档

组件集成了`springdoc-openapi-ui`, 通过访问`swagger-ui/index.html`阅读接口文档