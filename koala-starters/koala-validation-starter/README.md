# Koala Validation Starter

考拉代码校验启动模块

基于`spring-boot-starter-validation`, 提供接口参数校验功能

## 快速开始

### 实体类

在需要校验的实体类上增加校验注解:

```java
public class UserEntity {
    
  @NotBlank(message = "用户名不能为空")
  protected String name;
}
```

其余注解请参考[Jakarta Bean Validation API](https://jakarta.ee/specifications/bean-validation/3.0/apidocs/jakarta/validation/constraints/package-summary.html)

### 接口

在需要校验的接口上, 增加校验注解:

```java
public interface UserApi {
  @PostMapping
  DataResponse<User> add(@Validated @RequestBody UserEntity entity);
}
```

## 进阶

### 分组校验

在实际业务中, 可能会出现同一数据实体, 在新增和更新时的校验内容不同的情况

模块内置了两个分组接口`Create`和`Update`, 以新增为例:

```java
public class UserEntity {
    
  @NotBlank(message = "用户名不能为空", groups = Create.class)
  protected String name;
}

public interface UserApi {
  @PostMapping
  DataResponse<User> add(@Validated(Create.class) @RequestBody UserEntity entity);
}
```

如需自定义分组, 可继承`jakarta.validation.groups.Default`

### 自定义提示消息

校验失败提示消息可配置为如下格式:

```java
public class UserEntity {
    
  @NotBlank(message = "{user.name.not-blank}", groups = Create.class)
  protected String name;
}
```

在`resources`目录下创建配置文件`messages.properties`:

```properties
user.name.not-blank=用户名不能为空
```

