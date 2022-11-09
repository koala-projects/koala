# 设置组件

基于[数据模型组件](../koala-data-model-spring-boot-starter), 实现了简易设置功能, 适用于系统配置热更新等场景

## 初始化数据库

当前仅支持MySQL数据库, 推荐版本为8.0.X

1. 执行[数据库结构脚本](../../koala-domains/koala-data-model/koala-data-model-core/src/main/resources/schema-mysql.sql)

## 接口文档

组件集成了`springdoc-openapi-ui`, 通过访问`swagger-ui/index.html`阅读接口文档

## 快速获取设置内容

对于开发人员, 组件内置的`SettingService`提供了一种较为快捷的获取设置内容的方式:

```java
public class SettingTest {
  public void loadByKey() {
    Optional<Object> setting = settingService.loadByKey("system.default-password");
  }
}
```

设置键值的格式为: `${设置代码}.${设置项代码}`

## 设置注册

### 手动注册

组件内置了`SimpleSettingRegistry`, 可通过依赖注入完成手动注册:

```java
public class SettingTest {
  public void register() {
    MetadataEntity metadata = MetadataEntity.builder().id("999").code("system").name("系统设置").build();
    metadata.setProperties(List.of(
      PropertyEntity.builder().id("999-1").code("default-password").name("默认密码").type(PropertyType.STRING).build()
    ));
    settingRegistry.registerSetting(metadata, Map.of("default-password", "koala"));   
  }
}
```

### 自动注册

组件内置了`AutomaticSettingRegistry`, 可通过实现接口`SettingRegistration`完成自动注册:

```java
public class SettingRegistrationTest implements SettingRegistration {
  @Override
  public PersistentMetadata getMetadata() {
    MetadataEntity metadata = MetadataEntity.builder().id("999").code("system").name("系统设置").build();
    metadata.setProperties(List.of(
      PropertyEntity.builder().id("999-1").code("default-password").name("默认密码").type(PropertyType.STRING).build()
    ));
    return metadata;
  }

  @Override
  public Map<String, Object> getDefaults() {
    return Map.of("default-password", "koala");
  }
}
```

## Q&A

问: 如何同时使用数据模型组件和设置组件?

答: 可以为所有设置增加统一前缀, 如`_setting_`, 再自定义`SettingDefinitionService`, 实现设置与数据模型内容的分离