# Koala Cache Starter

考拉缓存启动模块, 基于 [Spring Data Redis](https://spring.io/projects/spring-data-redis) 和 [Spring Framework Cache](https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#cache), 快速集成缓存功能

## 快速开始

### 配置

```yaml
spring:
  data:
    redis:
      host: 127.0.0.1
      password: 123456
```

### 缓存注解

```java
@Component
public class UserService {
  
  @ListCacheable(value = "user-page")
  public List<String> page(Map<String, Object> parameters, Pageable pageable) {
    // 分页查询...
  }

  @LoadCacheable("user-load")
  public String load(Long id) {
    // 查看用户
  }
    
  @CacheEvict(value = "user-page", allEntries = true)
  public void create(User user) {
    
  }

  @Caching(evict = {
    @CacheEvict(value = "user-page", allEntries = true),
    @CacheEvict(value = "user-load", key = "#user.id"),
  })
  public void update(User user) {

  }
}
```

其余注解和注解参数请参照[官方文档](https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#cache-annotations)

## 进阶

### 缓存条件

模块抽象了缓存条件, 可通过实现接口`CacheCondition`自定义缓存条件:

```java
public class MyCacheCondition implements CacheCondition {
  
  @Override
  public boolean matches(Object target, Method method, Collection<Cache> caches, Object... params) {
    // 判断逻辑...
  }
}
```

实现缓存条件注册证, 注册缓存条件:

```java
@Component
public class MyCacheConditionRegistration implements CacheConditionRegistration {
  
  @Override
  public Set<String> getCacheNames() {
    return Set.of("my-cache", "your-cache");
  }
    
  @Override
  public CacheCondition getCacheCondition() {
    return new MyCacheCondition();
  }
}
```

使用缓存注解指定缓存条件:

```java
@Component
public class MyService {
  
  @Cacheable(value = "my-cache", condition = CacheNames.DEFAULT_CONDITION)
  public List<String> page(Map<String, Object> parameters, Pageable pageable) {
    // 分页查询...
  }
}
```

针对常用业务场景, 模块内置了如下缓存条件:

- 可选择的列表或分页查询缓存条件: `SelectiveListCacheCondition`

具体使用方式请参照对应类注释
