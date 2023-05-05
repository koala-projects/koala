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

如果这个检查是通用的, 除了`UserService`, 还需要重写更多的服务类

模块参照 [Spring Data JPA](https://spring.io/projects/spring-data-jpa), 结合[Spring AOP](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop-api), 增加了实体监听器的功能, 您需要让服务类继承自`AbstractCrudService`:

```java
public class UserService extends AbstractCrudService<User, Long> {}
```

实现`EntityListener`, 编写自定义监听器:

```java
public class UserEntityListener implements EntityListener {
    
  @PreUpdate
  public void preUpdate(User entity) {
    // 增加审计信息...
  }

  @Override
  public boolean support(Object entity) {
    return Objects.equals(User.class, entity.getClass());
  }
}
```

模块会自动解析注解`@PreUpdate`标注的方法, 并在更新前进行调用, 当前支持的生命周期有:

- `@PrePersist` / `@PostPersist` : 新增前后
- `@PreUpdate` / `@PostUpdate` : 更新前后
- `@PreRemove` / `@PostRemove` : 删除前后

如果您想要监听其他方法, 可以通过使用`@CrudAction`注解:

```java
public class MyUserService extends UserService {
  
  // value为CRUD操作类型, entity为实体参数索引
  @CrudAction(value = CrudType.UPDATE, entity = 0)
  public void setUserRoles(User entity, List<Role> roles) {
    // 业务逻辑...
  }
}
```

在调用监听方法时, 会传入被监听方法的**所有参数**:

```java
public class UserEntityListener implements EntityListener {
    
  @PreUpdate
  public void preUpdateRoles(User entity, List<Role> roles) {
    // 监听逻辑...
  }

  @Override
  public boolean support(Object entity) {
    return Objects.equals(User.class, entity.getClass());
  }
}
```

需要注意的是, 在如下场景中, **监听将会失效**:

- 继承自`AbstractCrudService`, 但在子类中重写了`create`/`update`/`delete`方法:

```java
public class UserService extends AbstractCrudService<User, Long> {
    
  // 重写方法导致更新监听失效
  // 可通过在方法上增加注解`@CrudAction(CrudType.UPDATE)`避免
  @Override
  public void update(User entity) {
    // 自定义更新逻辑...
  }
}
```

- 在同一对象内部进行方法调用:

```java
public class UserService extends AbstractCrudService<User, Long> {
  
  public void setUserRoles(User entity, List<Role> roles) {
    // 内部调用导致监听失效
    this.update(entity);
  }
}
```

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
