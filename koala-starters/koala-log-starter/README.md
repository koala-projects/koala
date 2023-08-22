# Koala Log Starter

考拉操作日志启动模块

## 快速开始

### 数据库

请先参照[快速开始](../../docs/guide/getting-started.md#初始化数据库)初始化数据库

### 日志注解

模块提供了`@Log`注解用于记录操作日志, 注解需要标注在**接口实现类**上:

```java
@RestController
public class DepartmentApiImpl implements DepartmentApi {

  @Override
  @Log(module = "部门管理", content = "查看部门详细信息")
  public DataResponse<Department> load(Long id) {
    return DataResponse.ok(service.load(id));
  }
}
```

注解属性`content`支持 SpEL, 示例如下:

```java
@RestController
public class DepartmentApiImpl implements DepartmentApi {

  @Override
  @Log(module = "部门管理", content = "查看部门[id=${#id}]详细信息")
  public DataResponse<Department> load(Long id) {
    return DataResponse.ok(service.load(id));
  }
}
```

需要将 SpEL 包裹在`${}`中, 模块会自动解析其中的内容, SpEL 书写方式请参照[官方文档](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#expressions)

## 进阶

### 高级配置

```yaml
koala:
  log:
    # 关闭日志
    enabled: false
    # 忽略模块正则表达式, 与@Log注解中的module进行匹配
    ignored-patterns:
      - 字典.*
```

