# Koala Web Application

考拉Web应用原型, 内置认证授权/系统管理/代码生成/附件管理等功能

## 快速开始

### 初始化

原型项目生成后, 使用`${项目名}-application/src/main/resources/database/init.sql`脚本初始化数据库

### 配置

修改配置文件中部分内容适配自己的开发环境:

```yaml
spring:
  datasource:
    # 数据库相关信息
    url: jdbc:mysql://127.0.0.1:3306/koala?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true
    username: koala
    password: koala
koala:
  # 附件相关信息
  attachment:
    root: D:\Temp\koala\attachment
  # 代码生成相关信息
  code:
    download-path: D:\Temp\koala\code\
```

### 接口文档

启动应用, 访问接口文档: `http://127.0.0.1:4200/swagger-ui.html`

![swagger-ui](https://github.com/koala-projects/image-hosting-service/blob/main/koala/koala-archetypes/koala-web-application/swagger-ui.png?raw=true)

## 进阶

### 开发接口

原型集成了代码生成功能, 可以根据数据库表快速开发增删改查接口

1. 修改数据库信息:

```sql
update t_database set url = 'jdbc:mysql://127.0.0.1:3306/test', username = 'test', `password` = 'test', catalog = 'test' where id = 1
```

2. 在数据库中创建业务表:

```sql
DROP TABLE IF EXISTS t_example;
CREATE TABLE t_example
(
  `id`                 BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name`               VARCHAR(100) NOT NULL COMMENT '示例名称',
  `sort_index`         INT                   DEFAULT 0 COMMENT '排序索引',
  `is_enabled`         INT          NOT NULL DEFAULT 1 COMMENT '是否启用',
  `is_systemic`        INT          NOT NULL DEFAULT 0 COMMENT '是否系统',
  `is_deleted`         INT          NOT NULL DEFAULT 0 COMMENT '是否删除',
  `created_by`         BIGINT       NOT NULL COMMENT '创建人ID',
  `created_time`       DATETIME     NOT NULL COMMENT '创建时间',
  `last_modified_by`   BIGINT COMMENT '最后更新人ID',
  `last_modified_time` DATETIME COMMENT '最后更新时间',
  `deleted_by`         BIGINT COMMENT '删除人ID',
  `deleted_time`       DATETIME COMMENT '删除时间',
  PRIMARY KEY (id)
) COMMENT = '示例表';
```

3. 参照[代码生成模块文档](../../koala-starters/koala-code-starter)生成代码:

```
|
|--api
|   |--ExampleApi.java
|   |--ExampleApiImpl.java
|--entity
|   |--ExampleEntity.java
...
...
```

4. 将代码复制到`${项目名}-application/src/main/java/${包名}`下
5. 在数据库中写入生成代码相关权限:

```sql
insert into k_permission(id, code, name, type, icon, url, component, parent_id, sort_index, is_systemic, created_by,
                         created_time)
values (10, 'example', '示例管理', 1, null, null, 'system/example/index.vue', null, 3, 1, 1, now()),
       (40, 'example:page', '示例列表', 2, null, null, null, 10, 301, 1, 1, now()),
       (41, 'example:load', '示例查询', 2, null, null, null, 10, 302, 1, 1, now()),
       (42, 'example:create', '示例创建', 2, null, null, null, 10, 303, 1, 1, now()),
       (43, 'example:update', '示例修改', 2, null, null, null, 10, 304, 1, 1, now()),
       (44, 'example:delete', '示例删除', 2, null, null, null, 10, 305, 1, 1, now());
```

