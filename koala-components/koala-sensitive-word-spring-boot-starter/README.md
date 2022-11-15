# koala-sensitive-word-spring-boot-starter

基于[ToolGood.Words](https://github.com/toolgood/ToolGood.Words)的敏感词过滤组件

提供如下功能:

- 敏感词过滤
- Jackson支持
- 自定义敏感词过滤器

## 引入依赖

```xml

<dependencies>
  <dependency>
    <groupId>cn.koala</groupId>
    <artifactId>koala-sensitive-word-spring-boot-starter</artifactId>
    <version>2022.0.2-SNAPSHOT</version>
  </dependency>
</dependencies>
```

## 敏感词过滤

```java

@RequiredArgsConstructor
public class TestService {

  private final RefreshableSensitiveWordFilterChain sensitiveWordFilterChain;

  public String filter(String text) {
    // 结果为: 单词[**]应该被过滤哦
    return sensitiveWordFilterChain.doFilter("单词[菜鸡]应该被过滤哦", '*');
  }
}
```

## Jackson支持

接口返回的对象中使用注解:

```java
public class TestObject {

  @JsonSerialize(using = SensitiveWordJsonSerializer.class)
  private String text;
}
```

默认替换字符为`*`, 如需指定可增加注解:

```java
public class TestObject {

  @JsonSerialize(using = SensitiveWordJsonSerializer.class)
  @Replacement('%')
  private String text;
}
```

## 自定义敏感词过滤器

组件提供了两种敏感词过滤器接口:

- 敏感词过滤器: `SensitiveWordFilter`
- 可刷新敏感词过滤器: `RefreshableSensitiveWordFilter`

组件提供了一种敏感词过滤器抽象类:

- `AbstractSensitiveWordFilter`

组件内置两种敏感词过滤器实现:

- 资源文件敏感词过滤器: `FileSensitiveWordFilter`
- 默认可刷新敏感词过滤器: `DefaultRefreshableSensitiveWordFilter`

手动编码实现敏感词过滤器:

```java

@Component
public class CustomFilter implements SensitiveWordFilter {

  @Override
  public String doFilter(String content, char replacement) {
    String result = content;
    // 过滤敏感词...
    return result;
  }
}
```