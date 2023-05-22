# Koala Sensitive Word Starter

考拉敏感词启动模块

## 快速开始

### 配置

模块内置了基于[ToolGood.Words](https://github.com/toolgood/ToolGood.Words)的敏感词过滤器, 可通过配置开启:

```yaml
koala:
  sensitive-word:
    # 词库文件仅支持文本格式, 一行代表一个敏感词
    file: /tmp/koala/sensitive-word/words.txt
    tool-good: true
```

### 敏感词过滤器

自动注入过滤器并使用:

```java
@RequiredArgsConstructor
public class UserService {
    
  private final SensitiveWordFilter filter;
    
  public String filterUserName(String username) {
    return filter.filter(username, '*');
  }
}
```

## 进阶

### 敏感词服务

模块将敏感词过滤器与敏感词服务进行了抽象分离, 可使用敏感词服务对词库进行管理:

```java
public class WordAddTask {
    
  private final SensitiveWordService service;
    
  public void addWord() {
    service.add("考拉");
  }
}
```

词库文件配置生效时, 会默认使用文件敏感词服务`FileSensitiveWordService`, 否则使用内存敏感词服务`InMemorySensitiveWordService`

如果敏感词服务不满足需求, 可通过实现敏感词服务接口`SensitiveWordService`进行自定义:

```java
@Component("sensitiveWordService")
public class MySensitiveWordService implements SensitiveWordService {

  @Override
  public List<String> list() {
    // 查询逻辑...
  }

  @Override
  public void add(String word) {
    // 新增逻辑...
  }

  @Override
  public void delete(String word) {
    // 删除逻辑...
  }
}
```

### 敏感词过滤器

如模块内置敏感词过滤器不满足需求, 您可以通过实现接口`SensitiveWordFilter`或`RefreshableSensitiveWordFilter`进行自定义:

```java
public class MySensitiveWordFilter implements RefreshableSensitiveWordFilter {

  @Override
  public String filter(String content, Character replacement) {
    // 过滤逻辑...
  }

  @Override
  public void refresh() {
    // 刷新逻辑...
  }
}
```

敏感词过滤支持多个过滤器同时生效:

```java
@Order(1)
public class FirstFilter implements SensitiveWordFilter {}

@Order(2)
public class SecondFilter implements SensitiveWordFilter {}
```

### Jackson支持

可通过指定序列化器来对接口返回值进行敏感词过滤:

```java
public class UserEntity {

  @JsonSerialize(using = SensitiveWordJsonSerializer.class)
  private String username;
}
```

支持过滤字符的定制:

```java
public class UserEntity {

  @JsonSerialize(using = SensitiveWordJsonSerializer.class)
  @Replacement('#')
  private String username;
}
```
