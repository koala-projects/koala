# koala-integration-mqtt-spring-boot-starter

结合Spring Integration实现的MQTT启动类

## 引入依赖

```xml

<dependencies>
  <dependency>
    <groupId>cn.houtaroy.koala</groupId>
    <artifactId>koala-integration-mqtt-spring-boot-starter</artifactId>
    <version>2022.0.0-SNAPSHOT</version>
  </dependency>
</dependencies>
```

## 书写配置

```yaml
spring:
  mqtt:
    connection:
      uris: [ tcp://127.0.0.1:1883 ]
    topics: [ test ]
    qos: [ 0 ]
```

## 创建消息拦截器

```java
public class MqttMessageHandler implements MessageHandler {

  @Override
  @ServiceActivator(inputChannel = "mqttInboundChannel")
  public void handleMessage(Message<?> message) {
    // ...
  }
}
```

## 发送消息

```java

@RequiredArgsConstructor
public class SendController {

  private final MqttTemplate mqttTemplate;

  public void send() {
    mqttTemplate.send("默认主题");
    mqttTemplate.send("topic-1", "指定主题, 默认qos: 1");
    mqttTemplate.send("topic-2", 2, "指定主题, qos: 2");
  }
}
```

## 动态新增或移除监听

```java

@RequiredArgsConstructor
public class SendController {

  private final MqttTemplate mqttTemplate;

  public void add() {
    mqttTemplate.addTopic("topic-3");
    mqttTemplate.addTopic("topic-3", 0);
    mqttTemplate.addTopic("topic-3", "topic-4");
    mqttTemplate.addTopic("topic-3", new MqttMessageListener());
  }

  public void remove() {
    mqttTemplate.removeTopic("topic-3");
    mqttTemplate.removeTopic("topic-3", "topic-4");
  }
}
```

其它可参照[示例项目](https://github.com/Houtaroy/koala/tree/main/koala-samples/koala-sample-mqtt)