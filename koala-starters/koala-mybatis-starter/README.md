# Koala MyBatis Starter

考拉MyBatis启动模块

基于[koala-persist-starter](../koala-persist-starter), 提供了 MyBatis 版本的持久化基础层实现

## 快速开始

1. 创建仓库接口:

```java
public interface UserRepository extends CrudRepository<UserEntity, Long> {}
```

2. 编写XML文件:

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="cn.koala.test.repositories.UserRepository">
    <!--查询语句-->
</mapper>
```

3. 创建服务类:

```java
@Component
public class UserService extends BaseMyBatisService<User, Long> {
  public UserService(UserRepository repository) {
    super(repository);
  }
}
```

4. 开始使用:

```java
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserApi {
    
  protected final UserService service;
  
  @GetMapping("{id}")
  public User load(@PathVariable("id") Long id) {
    return service.load(id);
  }
}
```

## 进阶

### 枚举增强支持

枚举增强请参照 [koala-persist-starter](../koala-persist-starter) 中的说明

在XML文件中, 可以直接引用增强枚举: `t.is_deleted = ${@cn.koala.persist.domain.YesNo@NO.value}`

自动装配`EnumAdviceTypeHandler`, 用于处理增强枚举在 MyBatis 中的值转换问题

### 分页查询

分页查询基于`Spring Data`和[MyBatis Pagehelper](https://github.com/pagehelper/Mybatis-PageHelper),
可以在接口添加分页参数`Pageable`, 调用服务类分页查询方法:

```java
@RestController
@RequestMapping("/users")
public class UserApi {
   
  @GetMapping
  public Page<User> page(Map<String, Object> parameters, Pageable pageable) {
    return service.page(parameters, pageable);
  }
}
```

请求如下:

```http
GET http://127.0.0.1:9000/user?page=0&size=10
```

### 自定义排序

分页参数支持排序, 请求如下:

```http
GET http://127.0.0.1:9000/user?page=0&size=10&sort=created_time,desc&sort=name,asc
```

服务类会自动将排序属性添加到参数`orders`中, 之后可修改`UserMapper.xml`:

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="cn.koala.test.repositories.UserRepository">
  <sql id="orderBy">
    <choose>
      <when test="orders != null and orders.size() > 0">
        <foreach collection="orders" item="order" index="index" open=" order by " close="" separator=",">
          <include refid="orderByField"/>
        </foreach>
      </when>
      <otherwise>
        order by t.created_time asc
      </otherwise>
    </choose>
  </sql>

  <sql id="orderByField">
    <!--注意: 如果前端传值错误, 此处会抛出异常-->
    <if test="order.property == 'name'">
      t.name <include refid="cn.koala.mybatis.common.CommonRepository.orderDirection" />
    </if>
    <if test="order.property == 'createdTime'">
      t.created_time <include refid="cn.koala.mybatis.common.CommonRepository.orderDirection" />
    </if>
  </sql>
    
  <select id="find" resultType="cn.koala.test.entities.UserEntity">
    <!--查询语句-->
    <include refid="orderBy"/>
  </select>
</mapper>
```

### 抽象实体类

提供了一个实现大部分持久化模型的抽象实体类`AbstractEntity`:

```java
public abstract class AbstractEntity implements Persistable<Long>, Sortable, Stateful, Auditable<Long> {}
```
