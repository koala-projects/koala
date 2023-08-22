# Koala Query Starter

考拉查询启动模块, 用于自定义SQL语句查询统计数据

## 快速开始

### 数据库

请先参照[快速开始](../../docs/guide/getting-started.md#初始化数据库)初始化数据库

### 接口文档

实现了查询管理接口, 可通过访问接口文档查看具体信息

接口文档地址: `http://${host}:${port}/swagger-ui.html`

### 新增查询定义

调用创建查询定义接口, 录入需要使用的自定义SQL:

```sql
select code, name, remark from t_dictionary where (:name is null or name = :name)
```

SQL语句写法参照[NamedParameterJdbcTemplate](https://docs.spring.io/spring-framework/reference/6.0-SNAPSHOT/data-access/jdbc/core.html#jdbc-NamedParameterJdbcTemplate)

### 执行查询

调用执行查询接口, 传递参数:

```http
GET http://localhost:4200/api/queries/1/execute?name=性别
```

## 进阶

### 分页参数

执行查询接口支持分页, 会自动注入如下参数:

- page: 页码
- size: 分页大小
- offset: 偏移量, 即 ( page - 1 ) * size

但分页逻辑需在自定义SQL中由编写人员手动实现, 如:

```sql
select code, name, remark from t_dictionary where (:name is null or name = :name) limit :offset, :size
```

