# Koala System Starter

考拉系统管理启动模块

## 快速开始

### 初始化

可通过如下两种方式初始化数据库:

1. 配置开启模块初始化器:

```yaml
koala:
  persist:
    initializer:
      system: true
```

2. 执行[脚本目录](../../koala-domains/koala-system/src/main/resources/database/system)下的结构脚本 `schema.sql` 和数据脚本 `data.sql`

### 接口文档

实现了字典管理/部门管理/角色管理/用户管理接口, 可通过访问接口文档查看具体信息

接口文档地址: `http://${host}:${port}/swagger-ui.html`

## 进阶

### 自定义

如现有系统管理功能不满足需求, 可以通过替换指定的服务或接口来实现定制

例如, 我们要增加一个批量创建部门的接口, 可以按如下步骤进行:

1. 创建自定义部门服务类`MyDepartmentService`, 实现`DepartmentService`接口:

```java
public class MyDepartmentService implements DepartmentService {
  @Override
  public void add(Department department) {
    // 实现原接口中的新增方法
  }

  // 新增自定义的批量创建方法
  public void addInBatch(List<Department> departments) {

  }
}
```

2. 创建自定义部门接口`MyDepartmentApi`, 继承`DepartmentApi`接口:

```java
public interface MyDepartmentApi extends DepartmentApi {
  // 增加自定义的批量创建接口
  @PreAuthorize("hasAuthority('system:department:create')")
  @Operation(summary = "批量创建部门")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DepartmentsResult.class))}
  )
  @PostMapping
  DataResponse<List<Department>> addInBatch(@RequestBody List<DepartmentEntity> entities);
}
```

3. 创建自定义部门接口实现类`MyDepartmentApiImpl`, 实现`MyDepartmentApi`:

```java
public class MyDepartmentApiImpl implements MyDepartmentApi {
  // 使用第一步创建的自定义部门服务类
  protected final MyDepartmentService service;

  @Override
  public DataResponse<Department> add(DepartmentEntity entity) {
    // 实现原接口中的新增接口
  }

  @Override
  public DataResponse<List<Department>> addInBatch(List<DepartmentEntity> entities) {
    // 实现自定义的批量创建接口
  }
}
```

4. 将自定义的类注入到Spring IOC容器中, 最简单的方法是使用注解, 需要注意Bean名称:

```java

@Component("departmentService")
public class MyDepartmentService implements DepartmentService {
}

@Component("departmentApi")
public class MyDepartmentApiImpl implements MyDepartmentApi {
}
```
