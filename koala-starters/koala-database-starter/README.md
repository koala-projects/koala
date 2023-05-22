# Koala Database Starter

考拉数据库管理启动模块

## 快速开始

### 初始化

可通过如下两种方式初始化数据库:

1. 配置开启模块初始化器:

```yaml
koala:
  persist:
    initializer:
      database: true
```

2. 执行[脚本目录](../../koala-domains/koala-database/src/main/resources/database/database)下的结构脚本 `schema.sql` 和数据脚本 `data.sql`

### 接口文档

实现了数据库管理接口, 可通过访问接口文档查看具体信息

接口文档地址: `http://${host}:${port}/swagger-ui.html`
