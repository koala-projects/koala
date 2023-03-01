# Koala Log Starter

考拉操作日志启动模块

## 日志注解

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

### SpEL

注解属性`content`支持SpEL, 示例如下:

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

需要将SpEL包裹在`${}`中, 模块会自动解析其中的内容, SpEL书写方式请参照[官方文档](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#expressions)

## 日志配置

### 是否启用

在某些场景下(如开发环境), 可能不需要开启操作日志, 可以通过配置文件进行关闭:

```yaml
koala:
  log:
    enabled: false
```

### 忽略

如您需要忽略部分操作日志记录, 可通过配置忽略表达式实现:

```yaml
koala:
  log:
    ignored-patterns:
      - 字典.*
```

忽略表达式支持正则匹配, 它会与注解`@Log`中的`module`属性进行匹配, 匹配成功则忽略

