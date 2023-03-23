# Koala MyBatis Starter

考拉MyBatis启动模块

基于[koala-persist-starter](../koala-persist-starter), 提供了 MyBatis 版本的持久化基础层实现

## 快速开始

1. 创建仓库接口:

```java
public interface MyRepository extends CrudRepository<MyEntity, Long> {}
```

2. 编写XML文件:

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="cn.koala.test.repositories.MyRepository">
    <!--查询语句-->
</mapper>
```

3. 创建服务类:

```java
@Component
public class MyService extends BaseMyBatisService<User, Long> {
  public MyService(MyRepository repository) {
    super(repository);
  }
}
```

4. 开始使用:

```java
@RestController
@RequestMapping("/my")
@RequiredArgsConstructor
public class MyApi {
    
  protected final MyService service;
  
  @GetMapping("{id}")
  public User load(@PathVariable("id") Long id) {
    return service.load(id);
  }
}
```

## 功能特点

### 全能抽象类

提供了一个实现大部分持久化模型的抽象实体类`BaseUniversalEntity`:

```java
public abstract class BaseUniversalEntity implements Persistable<Long>, Sortable, Stateful, Auditable<Long> {}
```

### 服务抽象类

提供了基于 MyBatis 的持久化服务抽象类`BaseMyBatisService<T, ID>`, 支持实体监听器, 采用[pagehelper](https://github.com/pagehelper/Mybatis-PageHelper)进行分页查询

### 增强枚举支持

在XML文件中, 可以直接引用增强枚举: `t.is_deleted = ${@cn.koala.persist.domain.YesNo@NO.value}`

自动装配`EnhancedEnumTypeHandler`, 用于处理增强枚举在 MyBatis 中的值转换问题
