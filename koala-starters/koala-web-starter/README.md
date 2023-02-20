# Koala Web Starter

考拉Web启动模块

## 响应类

提供了`Response`/`DataResponse`用于接口返回数据:

```java
// 返回成功 { code: 200, message: '请求成功' }
public Response success() {
    return Response.SUCCESS;
}

// 返回数据 { code: 200, message: '请求成功', data: [...] }
public DataResponse<User> create(UserEntity user) {
    return DataResponse.ok(user);
}

// 自定义返回 { code: 401, message: '没有授权' }
public Response custom() {
    return new Response(401, "没有授权");
}
```

## 异常拦截

自动装配`RestExceptionHandler`, 用于拦截异常, 返回信息可查看源码

## 接口文档

集成了`springdoc-openapi`, 可通过`http://${host}:${port}/swagger-ui.html`地址访问接口文档
