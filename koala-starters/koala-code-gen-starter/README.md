# Koala Code Gen Starter

考拉代码生成启动模块

集成了[数据库模块](../koala-database-starter)和[模板模块](../koala-template-starter)全部功能, 实现了从数据库表到代码的生成过程

## 快速开始

### 数据库

请先参照[快速开始](../../docs/guide/getting-started.md#初始化数据库)初始化数据库

### 配置

根据实际情况, 进行参数配置:

```yaml
koala:
  code-gen:
    # 生成代码的包名
    package-name: cn.koala.test
    # 表前缀
    table-prefix: t_
    # 下载路径
    download-path: /tmp/koala/code
```

### 接口文档

实现了数据库管理/模板管理/代码生成接口, 可通过访问接口文档查看具体信息

接口文档地址: `http://127.0.0.1:4200/swagger-ui.html`

## 进阶

### 代码生成核心逻辑

```mermaid
flowchart LR
	数据库表 --代码生成上下文处理器--> 代码生成上下文 --代码生成器 --> 代码
```

### 代码生成上下文处理器

代码生成上下文处理器用于生成代码生成上下文, 系统内置的处理器提供如下数据:

| 键值          | 类型                                              | 描述                                                         |
| ------------- | ------------------------------------------------- | ------------------------------------------------------------ |
| `table`       | [表对象](#表对象)                                 | 数据库表对象                                                 |
| `package`     | 字符串                                            | Java项目包名, 对应配置`koala.code-gen.package-name`          |
| `name`        | [名称对象](#名称对象)                             | 截取前缀配置`koala.code-gen.table-prefix`后的领域名称, 例如: `User` |
| `description` | 字符串                                            | 领域描述, 依据表备注生成, 例如: `用户`                       |
| `id`          | [领域属性对象](#领域属性对象)                     | 主键的领域属性对象                                           |
| `properties`  | [领域属性对象](#领域属性对象)列表                 | 领域属性对象列表                                             |
| `entity`      | [实体代码生成上下文对象](#实体代码生成上下文对象) | 实体代码生成上下文对象                                       |

#### 表对象

| 键值      | 类型                  | 描述                         |
| --------- | --------------------- | ---------------------------- |
| `name`    | 字符串                | 数据库表名称, 例如: `t_user` |
| `remarks` | 字符串                | 数据库表备注, 例如: `用户表` |
| `columns` | [列对象](#列对象)集合 | 数据库列对象集合             |

#### 列对象

| 键值              | 类型   | 描述                                                         |
| ----------------- | ------ | ------------------------------------------------------------ |
| `name`            | 字符串 | 数据库列名称, 例如: `id`                                     |
| `type`            | 整型   | 数据库列的[JDBC类型](https://docs.oracle.com/en/java/javase/17/docs/api/java.sql/java/sql/JDBCType.html) |
| `size`            | 整型   | 数据库列长度, 例如: `100`                                    |
| `decimalDigits`   | 整型   | 数据库列精度, 例如: `6`                                      |
| `remarks`         | 字符串 | 数据库列备注, 例如: `用户表`                                 |
| `isNullable`      | 布尔值 | 数据库列是否允许为空, 例如: `true`                           |
| `isAutoincrement` | 布尔值 | 数据库列是否自增, 例如: `true`                               |
| `isPrimaryKey`    | 布尔值 | 数据库列是否为主键, 例如: `true`                             |

#### 领域属性对象

| 键值          | 类型                  | 描述                                                       |
| ------------- | --------------------- | ---------------------------------------------------------- |
| `name`        | [名称对象](#名称对象) | 领域属性名称, 例如: `sortIndex`                            |
| `description` | 字符串                | 领域属性描述, 例如: `排序索引`                             |
| `type`        | 字符串字典            | 领域类型字典, 例如: `{ 'java': 'Long', 'json': 'number' }` |

#### 名称对象

| 键值     | 类型                          | 描述   |
| -------- | ----------------------------- | ------ |
| `pascal` | [名称风格对象](#名称风格对象) | 大驼峰 |
| `camel`  | [名称风格对象](#名称风格对象) | 小驼峰 |
| `kebab`  | [名称风格对象](#名称风格对象) | 短横风 |
| `snake`  | [名称风格对象](#名称风格对象) | 下划线 |

#### 名称风格对象

| 键值       | 类型   | 描述                          |
| ---------- | ------ | ----------------------------- |
| `singular` | 字符串 | 单数格式字符串, 例如: `user`  |
| `plural`   | 字符串 | 复数格式字符串, 例如: `users` |

#### 实体代码生成上下文对象

| 键值          | 类型                          | 描述                                           |
| ------------- | ----------------------------- | ---------------------------------------------- |
| `isAbstract`  | 布尔值                        | 是否继承抽象类                                 |
| `validations` | [校验对象字典](#校验对象字典) | 校验对象字典, 例如: `{ 'sortIndex': [ ... ] }` |

#### 校验对象字典

| 键值         | 类型       | 描述                                         |
| ------------ | ---------- | -------------------------------------------- |
| `name`       | 字符串     | 校验名称, 例如: `NotNull`                    |
| `parameters` | 字符串字典 | 校验参数字典, 例如: `{ 'max': 10 }`          |
| `message`    | 字符串     | 校验信息, 例如: `排序索引不能为空`           |
| `groups`     | 字符串集合 | 校验分组列表, 例如: `[ 'Create', 'Update' ]` |

如上述内容不满足需求, 可通过实现接口`ContextProcessor`来创建自定义上下文处理器:

```java
public class MyContextProcessor implements CodeGenContextProcessor {

  @Override
  public CodeGenContext process(DatabaseTable table) {
    // 构建代码生成上下文逻辑...
  }
}
```

### 自定义代码生成器

可通过实现代码生成器接口 `CodeGenerator` 自定义代码生成器:

```java
@Component
public class MyCodeGenerator implements CodeGenerator {

  @Override
  public CodeGenResult generate(DatabaseTable table, Template template) {
    // 代码生成逻辑
  }
}
```

系统内置了如下代码生成器:

- `TemplateEngineCodeGenerator`: 基于模板引擎的代码生成器, 默认使用[Enjoy模板引擎](https://jfinal.com/doc/6-1), 可通过替换 `TemplateRenderer` 更换模板引擎

### 自定义模板

可以通过集成的模板管理功能创建自定义模板, 例如新增一个Python的代码模板:

```python
import UserRepository as repository

def add(#(name)Entity):
    repository.add(#(name)Entity)
```

模板支持多级路径生成, 假设目录结构如下:

```
apis
 |--Api.java
 |--ApiImpl.java
```

可以将模板文件名称设置为`api/#(name.pascal.singular)Api.java`

