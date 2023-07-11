# Koala Template Starter

考拉模板管理启动模块

## 快速开始

### 初始化

可通过如下两种方式初始化数据库:

1. 配置开启模块初始化器:

```yaml
koala:
  persist:
    initializer:
      template: true
```

2. 执行[脚本目录](../../koala-domains/koala-template/src/main/resources/database/template)下的结构脚本 `schema.sql` 和数据脚本 `data.sql`

### 渲染器

根据想要使用的模板引擎, 配置或创建自己的渲染器:

```java
public class ThymeleafTemplateRenderer implements TemplateRenderer {

  @Override
  public String render(Template template, Map<?, ?> context) {
    // 渲染逻辑
  }
}
```

模块内置了[Enjoy模板引擎](https://gitee.com/jfinal/enjoy/tree/master)渲染器, 在Maven中手动添加依赖即可自动装配:

```xml
<dependency>
    <groupId>com.jfinal</groupId>
    <artifactId>enjoy</artifactId>
    <version>{enjoy.version}</version>
</dependency>
```

### 接口文档

实现了模板组管理/模板管理功能, 可通过访问接口文档查看具体信息

接口文档地址: `http://${host}:${port}/swagger-ui.html`

## 进阶

### 模板组与模板

因考虑到实际业务中的使用情况, 增加了模板组的概念

模板组与模板为一对多关系, 但并非强制绑定, 如您不需要模板组概念, 可直接忽略相关内容
