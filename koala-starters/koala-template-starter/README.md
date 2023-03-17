# Koala Template Starter

考拉模板管理启动模块

## 模板管理接口

实现了模板组管理/模板管理接口, 可通过访问接口文档查看具体信息

接口文档地址: `http://${host}:${port}/swagger-ui.html`

### 初始化

在启动前请使用[脚本](../../koala-domains/koala-template/src/main/resources/database/init.sql)初始化数据库, 当前仅支持 MySQL 8

### 模板组与模板

因考虑到实际业务中的使用情况, 增加了模板组的概念

模板组与模板为一对多关系, 但并非强制绑定, 如您不需要模板组概念, 可直接忽略相关内容
