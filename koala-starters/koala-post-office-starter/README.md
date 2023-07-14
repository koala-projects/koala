# Koala Post Office Starter

考拉驿站启动模块, 提供了 邮件 / 短信 / 企业微信 等消息发送功能

## 快速开始

### 引入依赖

本模块不包含消息发送的第三方依赖, 需要根据实际使用方式手动引入:

```xml
<!-- 邮件 -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-mail</artifactId>
</dependency>

<!-- 短信 -->
<dependency>
  <groupId>com.aliyun</groupId>
  <artifactId>dysmsapi20170525</artifactId>
</dependency>

<!-- 企业微信 -->
<dependency>
  <groupId>com.github.binarywang</groupId>
  <artifactId>wx-java-cp-spring-boot-starter</artifactId>
</dependency>
```

### 配置

```yaml
# 邮件配置
spring:
  mail:
    host: smtp.yeah.net
    username: koala_projects@yeah.net
    password: 123456
    
# 企业微信配置
wx:
  cp:
    corp-id: your-corp-id
    corp-secret: your-corp-secret
    agent-id: 100000

# 阿里云短信配置
koala:
  post-office:
    access-key-id: your-access-key-id
    access-key-secret: your-access-key-secret
```

### 接口文档

实现了驿站管理接口, 可通过访问接口文档查看具体信息

接口文档地址: `http://${host}:${port}/swagger-ui.html`

### 发送消息

调用驿站管理的投递接口发送消息:

```http
POST http://127.0.0.1:4200/api/post-offices/post
Content-Type: application/json

{
    "data": [
        {
            "officeName": "simple-mail",
            "original": {
                "from": "koala_projects@yeah.net",
                "to": "hyh_work@yeah.net",
                "subject": "测试消息",
                "text": "测试消息"
            }
        },
        {
            "officeName": "wework",
            "original": {
                "toUser": "zhangsan",
                "msgType": "text",
                "content": "测试消息"
            }
        },
        {
            "officeName": "aliyun-sms",
            "original": {
                "PhoneNumbers": "1390000****",
                "SignName": "阿里云",
                "TemplateCode": "SMS_15305****"
            }
        }
    ]
}
```

返回结果:

```json
{
  "code": 200,
  "message": "请求成功",
  "data": [
    {
      "officeName": "wework",
      "isSuccess": true
    },
    {
      "officeName": "simple-mail",
      "isSuccess": true
    },
    {
      "officeName": "aliyun-sms",
      "isSuccess": false,
      "error": "驿站不存在"
    }
  ]
}
```

## 进阶

### 自定义驿站

可实现`PostOffice`接口自定义驿站:

```java
public class MyPostOffice implements PostOffice {

  @Override
  public String getName() {
    // 返回驿站名称逻辑...
  }

  @Override
  public void post(Map<String, Object> parcel) throws Exception {
    // 投递逻辑...
  }
}
```

模块内置了如下驿站:

- `SimpleMailPostOffice`: 简易邮件驿站, 用于发送文本邮件
- `WeworkPostOffice`: 企业微信驿站, 使用指定应用向企业微信用户发送消息
- `AliyunSmsPostOffice`: 阿里云短信驿站, 用于发送短信
- `WechatMiniappPostOffice`: 微信小程序驿站, 用于发送订阅消息通知

