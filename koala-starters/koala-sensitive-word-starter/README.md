# Koala Sensitive Word Starter

考拉敏感词启动模块

## 快速开始

1. 引入模块依赖
2. 配置词库文件地址:

```yaml
koala:
  sensitive-word:
    word-file: /tmp/koala/sensitive-word/words.txt
```

词库文件当下仅支持文本格式, 一行代表一个敏感词, 如不满足需求可参考[自定义](#自定义)

3. 自动注入过滤器:

```java
@RequiredArgsConstructor
public class UserService {
    
  private final SensitiveWordFilter filter;
    
  public String filterUserName(String username) {
    return filter.filter(username, '*');
  }
}
```

## 功能特点

### 敏感词服务

模块内置了敏感词服务, 可对敏感词进行增删查操作:

```java
public class WordAddTask {
    
  private final SensitiveWordService service;
    
  public void addWord() {
    service.add("考拉");
  }
}
```

### 敏感词过滤器

模块内置了敏感词过滤器, 可通过自动注入方式引用

内置的过滤器支持动态刷新词库:

```java
public class WordRefreshTask {
    
  private final SensitiveWordService service;
  private final SensitiveWordFilter filter;
    
  public void refresh() {
    if(filter instanceof RefreshableSensitiveWordFilter refreshable) {
      refreshable.refresh(service.list());
    }
  }
}
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

## 自定义

### 自定义敏感词服务

模块默认敏感词服务`InMemorySensitiveWordService`基于内存实现

如不满足需求, 可手动实现`SensitiveWordService`, 定制自己的敏感词服务:

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

### 自定义过滤器

模块默认敏感词过滤器`SimpleSensitiveWordFilter`基于[ToolGood.Words](https://github.com/toolgood/ToolGood.Words)实现

如不满足需求, 可手动实现`SensitiveWordFilter`或`RefreshableSensitiveWordFilter`, 定制自己的过滤器:

```java
@Component("sensitiveWordFilter")
public class MySensitiveWordFilter implements RefreshableSensitiveWordFilter {

  @Override
  public String filter(String content, Character replacement) {
    // 过滤逻辑...
  }

  @Override
  public void refresh(List<String> words) {
    // 刷新逻辑...
  }
}
```



