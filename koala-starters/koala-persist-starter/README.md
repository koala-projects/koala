# Koala Persist Starter

考拉持久化启动模块

基于 Spring Data, 定义了增删改查等部分通用持久化操作, 包含模型/仓库/服务/实体监听器等

## 快速开始

本模块为基础模块, 主要用于构建数据访问基础层, 不具备单独使用的能力

提供了 MyBatis 版本的实现, 可参照[koala-mybatis-starter](../koala-mybatis-starter)

## 功能特点

### 持久化模型

在 Spring Data 的基础上, 调整并增加了部分模型:

| 模型名称         | 模型介绍                                                     |
| ---------------- | ------------------------------------------------------------ |
| `Persistable<T>` | 可持久化的模型, 定义了名为`id`的主键属性, `T`为主键类型      |
| `Sortable`       | 可排序的模型, 定义了名为`sortIndex`的排序索引属性            |
| `Stateful`       | 有状态的模型, 定义了是否启用/是否系统/是否删除状态属性       |
| `Auditable<ID>`  | 可审计的模型, 定义了创建者主键/创建时间等用于审计的属性, `ID`为审计者主键类型 |
| `EnhancedEnum`   | 增强枚举, 为枚举增加了`name`属性                             |

### 持久化操作

在 Spring Data 的基础上, 调整并增加了部分操作:

| 操作名称                 | 操作介绍                                                     |
| ------------------------ | ------------------------------------------------------------ |
| `CrudRepository<T, ID>`  | 持久化仓库接口, 包含增删改查等方法, `T`为实体类型, `ID`为主键类型 |
| `CrudService<T, ID>`     | 持久化服务接口, 在仓库接口的基础上增加了分页方法             |
| `BaseCrudService<T, ID>` | 持久化服务抽象类, 提供了部分方法实现                         |

### 实体监听器

在持久化过程中, 经常会遇到类似数据库触发器一般的需求, 例如更新前的数据安全检查

这种前置操作可能本身并不复杂, 但开发者往往需要继承对应的服务类并重写方法:

```java
class MyUserService extends UserService {
  @Override
  public void update(User user) {
    // 更新检查...
    super.add(user);
  }
}
```

如果这个检查是通用的, 除了`UserService`, 还需要重写更多的服务类

参照 Spring Data JPA, 模块提供了相对更为直观的`EntityListener`, 用于抽象和简化上述需求

提供了两个默认实体监听器:

- `StatefulEntityListener`: 有状态的实体监听器, 根据操作自动设置状态属性的值
- `AuditingEntityListener`: 可审计的实体监听器, 用于自动写入审计信息

如需要增加自己的实体监听器, 请参照[自定义实体监听器](#自定义实体监听器)

## 自定义

### 自定义审计者发现器

在进行审计操作时, 需要根据项目实际的用户权限体系, 提供审计者ID

创建审计者发现器, 实现接口:

```java
@Component
public class MyAuditorAware implements AuditorAware<Long> {
  @Override
  @NonNull
  public Optional<Long> getCurrentAuditor() {
    // 返回审计者ID
  }
}
```

### 自定义实体监听器

1. 创建实体监听器, 实现接口:

```java
// 实体监听器支持排序
@Order(100)
// 实体监听器需要装载到 Spring IOC 容器中
@Component
public class MyEntityListener implements EntityListener {
    
  @Override
  public void beforeAdd(Object entity) {
    // 新增前置操作...
  }
  
  // entity为将要更新的实体数据
  // persist为数据库持久化的实体数据
  @Override
  public void beforeUpdate(Object entity, Object persist) {
	// 更新前置操作
  }
}
```

2. 如果实体监听器只对特定实体生效, 实现`EntityListenerSelector`接口:

```java
@Order(100)
@Component
public class MyEntityListener implements EntityListener, EntityListenerSelector {
    
  @Override
  public boolean match(Class<?> entityType) {
    // 判断实体类型是否匹配
  }
}
```

3. 需要实体监听器的持久化服务, 实现`EntityListenerSupport`接口:

```java
@Component
public class MyService implements EntityListenerSupport {

  @Override
  public void registerListener(EntityListener listener) {
    // 注册监听器
  }

  @Override
  public void registerListeners(List<EntityListener> listeners) {
    // 批量注册监听器
  }
    
  @Override
  public Optional<Class<?>> getEntityType() {
    // 获取实体类型
  }
}
```

模块内置了`BaseEntityListener`和`BaseListenableCrudService`, 实现了上述接口的部分方法, 供开发者参考
