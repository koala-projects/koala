# Koala Persist Starter

考拉持久化启动模块

基于 [Spring Data](https://spring.io/projects/spring-data), 定义了增删改查等部分通用持久化操作, 包含模型/仓库/服务/实体监听器等

## 快速开始

本模块为基础模块, 主要用于构建数据访问基础层, 数据层操作需自行完成

提供了 MyBatis 版本的实现, 可参照 [koala-mybatis-starter](../koala-mybatis-starter)

### 基础模型

在 Spring Data 的基础上, 调整并增加了部分模型:

| 模型名称                     | 模型介绍                                                     |
| ---------------------------- | ------------------------------------------------------------ |
| `Persistable<T>`             | 可持久化的模型, 定义了名为`id`的主键属性, `T`为主键类型      |
| `Sortable`                   | 可排序的模型, 定义了名为`sortIndex`的排序索引属性            |
| `Stateful`                   | 有状态的模型, 定义了是否启用/是否系统/是否删除状态属性       |
| `Auditable<ID>`              | 可审计的模型, 定义了创建者主键/创建时间等用于审计的属性, `ID`为审计者主键类型 |
| `CrudRepository<T, ID>`      | 持久化仓库接口, 包含增删改查等方法, `T`为实体类型, `ID`为主键类型 |
| `CrudService<T, ID>`         | 持久化服务接口, 在仓库接口的基础上增加了分页方法             |
| `AbstractCrudService<T, ID>` | 持久化服务抽象类, 提供了部分方法实现                         |

### 枚举增强

模块提供了枚举增强接口`EnumAdvice`, 在枚举的基础上, 增加了名称属性:

```java
@Getter
public enum YesNo implements EnumAdvice {
  YES("是", 1),
  NO("否", 0);

  private final String name;
  private final int value;

  YesNo(String name, int value) {
    this.name = name;
    this.value = value;
  }
}
```

## 进阶

### 实体监听器

在持久化过程中, 经常会遇到类似数据库触发器一般的需求, 例如增加审计信息

这种前置操作可能本身并不复杂, 但开发者往往需要继承对应的服务类并重写方法:

```java
public class MyUserService extends UserService {
  
  @Override
  public void update(User user) {
    // 增加审计信息...
    super.add(user);
  }
}
```

为了尽可能的实现业务职责单一, 减少重写方法的心智负担, 模块参照 [Spring Data JPA](https://spring.io/projects/spring-data-jpa), 结合 [Spring AOP](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop-api), 增加了实体监听器的功能

模块会自动解析 JPA 注解标注的方法, 当前支持的生命周期有:

- `@PrePersist` / `@PostPersist` : 新增前后
- `@PreUpdate` / `@PostUpdate` : 更新前后
- `@PreRemove` / `@PostRemove` : 删除前后

需要注意的是, 在如下场景中**监听将会失效**:

- 在同一对象内部进行方法调用:

```java
public class UserService extends AbstractCrudService<User, Long> {
  
  public void setUserRoles(User entity, List<Role> roles) {
    // 内部调用导致监听失效
    this.update(entity);
  }
}
```

#### 自定义实体监听器

1. 基于 JPA 书写自定义监听器:

```java
// 自定义实体监听器会按照排序执行
@Order(1000)
// 自定义实体监听器需被 Spring IOC 容器管理
@Component
public class UserEntityListener implements EntityListener {
    
  @PreUpdate
  public void preUpdate(User entity) {
    // 增加审计信息...
  }
}
```

2. 指定实体所需要的自定义实体监听器:

```java
@EntityListeners(UserEntityListener.class)
public class UserEntity {
    
}
```

#### 系统实体监听器

如果业务逻辑针对大部分或全部实体生效, 可实现系统实体监听器接口`SystemEntityListener`:

```java
@Order(1000)
@Component
public class AuditingEntityListener implements SystemEntityListener {
    
  @PrePersist
  public void prePersist(Auditable<Long> entity) {
    // 审计逻辑...
  }
    
  @Override
  public boolean support(Class<?> entityClass) {
    // 判断是否支持当前实体...
  }
}
```

#### 事务管理

在使用实体监听器时, 切面会手动进行**事务管理**, 管理逻辑如下:

1. 若当前存在事务, 则加入, 否则开启新事务
2. 事务会在所有实体监听器运行结束后提交
3. 若实体监听器抛出运行时异常, 则自动进行回滚

### 审计员感知器

在实际业务中, 我们可能会需要获取当前登录的用户作为审计对象, 例如创建用户时

可以通过实现`AuditorAware`接口进行自定义:

```java
public class MyAuditorAware implements AuditorAware<MyUser> {

  @Override
  @NonNull
  public Optional<MyUser> getCurrentAuditor() {
    // 获取当前用户逻辑...
  }
}
```

> 默认审计操作是通过实体监听器实现的哦

### 数据校验

基于[Jakarta Bean Validation](https://beanvalidation.org/), 模块内置了几个自定义校验器, 方便处理部分通用校验逻辑

1. 根据id判断数据是否可编辑`@EditableId`:

```java
// 需在接口类上标注注解@Validated
@Validated
public interface UserApi {
  
  // 使用可编辑ID校验时, 需设置实体类型属性
  @DeleteMapping("{id}")
  Response delete(@EditableId(UserEntity.class) @PathVariable("id") Long id);
}
```

2. 字段是否唯一校验`@UniqueField`:

```java
// 字段唯一校验需标注在实体类上
// 使用字段唯一校验时, 需设置校验字段名称属性
// 使用字段唯一校验时, 列表查询方法需支持校验字段作为查询参数
@UniqueField("username")
// 如查询参数与校验字段名称不一致, 需设置parameter属性
@UniqueField(value = "nickname", parameter = "nicknameExact", message = "昵称已存在")
public class UserEntity {
  
  private String username;
  
  private String nickname;
}

public interface UserApi {
  
  // 在需校验的实体参数前标注注解@Validated
  @PostMapping
  DataResponse<User> create(@Validated @RequestBody UserEntity user);
}
```
