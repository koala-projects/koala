# koala-dingtalk-spring-boot-starter

基于Druid拓展启动类, 提供查询语句解析和SQL检查功能

## 引入依赖

```xml

<dependencies>
  <dependency>
    <groupId>cn.koala</groupId>
    <artifactId>koala-dingtalk-spring-boot-starter</artifactId>
    <version>2022.0.0-SNAPSHOT</version>
  </dependency>
</dependencies>
```

## 书写配置

```yaml
ding-talk:
  agent-Id: test
  app-key: key
  app-secret: secret
```

## 使用旧版SDK

自动装配了`DingtalkService`的实现`InMemoryDingtalkService`

可直接执行旧版SDK请求:

```java
@RequiredArgsConstructor
public class MyService {
  private final DingtalkService dingtalkService;
    
  public OapiMessageCorpconversationAsyncsendV2Response message(DbType dbType, String sql) throws Exception {
    OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
    request.setAgentId(agentId);
    request.setUseridList("1,2");
    request.setToAllUser(false);
    OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
    msg.setOa(new OapiMessageCorpconversationAsyncsendV2Request.OA());
    msg.getOa().setHead(new OapiMessageCorpconversationAsyncsendV2Request.Head());
    msg.getOa().getHead().setText("标题");
    msg.getOa().setBody(new OapiMessageCorpconversationAsyncsendV2Request.Body());
    msg.getOa().getBody().setContent("测试消息");
    msg.setMsgtype("oa");
    request.setMsg(msg);
    return dingtalkService.execute("https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2", request);
  }
} 
```

旧版SDK自动处理了用户token

## 使用新版SDK

新版SDK调用时与旧版有所不同, 故提供`client`方法用于获取新版请求客户端, 之后请自行请求:

```java
@RequiredArgsConstructor
public class MyService {
  private final DingtalkService dingtalkService;
    
  public GetAccessTokenResponse token(String appKey, String appSecret) throws Exception {
    Optional<com.aliyun.dingtalkoauth2_1_0.Client> client = dingtalkService.client(com.aliyun.dingtalkoauth2_1_0.Client.class);
    if (client.isPresent()) {
      GetAccessTokenRequest request = new GetAccessTokenRequest();
      request.setAppKey(appKey).setAppSecret(appSecret);
      return client.get().getAccessToken(request);
    }
    return null;
  }
} 
```