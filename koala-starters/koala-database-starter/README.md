# Koala Database Starter

考拉数据库管理启动模块

## 数据库管理接口

实现了数据库管理接口, 可通过访问接口文档查看具体信息

接口文档地址: `http://${host}:${port}/swagger-ui.html`

### 初始化

在启动前请使用[脚本](../../koala-domains/koala-database/src/main/resources/database/init.sql)初始化数据库, 当前仅支持 MySQL 8

### 数据库信息查询

默认实现了查询数据库表列表/查看数据库指定表接口

可通过调用它们获取数据库表和列的定义
