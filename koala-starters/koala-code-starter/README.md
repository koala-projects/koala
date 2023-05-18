# Koala Code Starter

考拉代码生成启动模块

集成了[数据库模块](../koala-database-starter)和[模板模块](../koala-template-starter)全部功能, 实现了从数据库表到代码的生成过程

## 快速开始

### 初始化

在启动前请使用[脚本](src/main/resources/database/init.sql)初始化数据库, 当前仅支持 MySQL 8

如提示表不存在, 需要先使用上述集成模块的初始化脚本

### 配置

根据实际情况, 进行参数配置:

```yaml
koala:
  code:
    # 生成代码的包名
    package-name: cn.koala.test
    # 表前缀
    table-prefix: t_
    # 下载路径
    download-path: /tmp/koala/code
```

### 接口文档

实现了数据库管理/模板管理/代码生成接口, 可通过访问接口文档查看具体信息

接口文档地址: `http://${host}:${port}/swagger-ui.html`

## 功能特点

默认实现了基于模板引擎的代码生成抽象类`BaseTemplateCodeService`, 它的核心处理逻辑如下:

```mermaid
flowchart LR
	数据库表 --上下文处理器--> 上下文 --模板引擎--> 代码
```

### 上下文处理器

上下文处理器用于生成模板渲染的上下文, 系统内置的处理器提供如下参数:

| 键值          | 类型              | 描述                                          |
| ------------- | ----------------- | --------------------------------------------- |
| `package`     | 字符串            | 配置文件中的`koala.code.package-name`         |
| `table`       | 表对象            | 数据库表对象, 参照`SimpleDatabaseTable`       |
| `columns`     | 列数组            | 数据库列数组, 参照`SimpleDatabaseTableColumn` |
| `name`        | 字符串            | 领域名称, 例如`t_user`表则为`User`            |
| `pluralName`  | 字符串            | 领域名称复数形式, 例如`t_user`表则为`Users`   |
| `description` | 字符串            | 领域描述, 依据表备注生成                      |
| `api`         | 接口上下文对象    | 接口上下文对象, 参照`ApiContext`              |
| `entity`      | 实体上下文对象    | 实体上下文对象, 参照`EntityContext`           |
| `mybatis`     | MyBatis上下文对象 | MyBatis上下文对象, 参照`MyBatisContext`       |

如上述内容不满足需求, 可通过实现接口`ContextProcessor`来创建自定义上下文处理器:

```java
public class MyContextProcessor implements ContextProcessor {

  @Override
  public Map<String, Object> process(Object context) {
    // 上下文生成逻辑...
  }
}
```

### 自定义模板

模块默认使用[Enjoy](https://jfinal.com/doc/6-1)模板引擎, 内置了一套适用于考拉的代码模板组

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

可以将模板文件名称设置为`api/Api.java`

如需更换模板引擎, 可通过继承`BaseTemplateCodeService`类, 创建自己的代码服务:

```java
public class MyCodeService extends BaseTemplateCodeService {

  public MyCodeService(ContextProcessor processor, String downloadPath) {
    super(processor, downloadPath);
  }

  @Override
  protected Code generate(@NonNull Template template, Map<String, Object> context) {
    // 模板引擎生成逻辑...
  }
}
```

