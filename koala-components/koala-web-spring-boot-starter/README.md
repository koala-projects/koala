# Web组件

对部分通用Web功能进行集成和拓展

## 通用返回值

```java
public class TestApi {
  
  public Response res() {
    return Response.SUCCESS;
  }

  public DataResponse<String> data() {
    return DataResponse.ok("data");
  }
}
```

## 统一异常拦截

所有异常会被捕获, 并返回以下结构错误信息:

```json
{
  "code": 500,
  "message": "xxx"
}
```

## JSON 拓展

增加 LocalDateTime 和时间戳的相互转换

```java
public class TestEntity {
  private LocalDateTime date;
}
```

```json
{
  "date": 1668005229000
}
```

