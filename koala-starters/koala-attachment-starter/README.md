# Koala Attachment Starter

考拉附件启动模块, 实现附件上传和下载功能

## 快速开始

### 初始化

使用[脚本](../../koala-domains/koala-attachment/src/main/resources/database/init.sql)初始化数据

### 配置

配置附件根目录:

```yaml
koala:
  attachment:
    root: /tmp/attachment
```

### 上传文件

```http
POST http://127.0.0.1:9000/api/attachments/upload
Content-Type: multipart/form-data
attachment=头像.png
```

### 下载文件

```http
GET http://127.0.0.1:9000/api/attachments/1/download
```

## 进阶

### 对象存储

模块增加了MinIO对象存储支持, 需进行如下配置:

1. 引入MinIO启动模块:

```xml
<dependency>
  <groupId>cn.koala</groupId>
  <artifactId>koala-minio-starter</artifactId>
</dependency>
```

2. 配置附件类型和对象存储参数:

```yaml
koala:
  attachment:
    type: minio
  minio:
    endpoint: http://127.0.0.1:9000
    access-key: minioadmin
    secret-key: minioadmin
```

### 自定义附件存储

模块将附件存储抽象出来, 与持久化逻辑进行拆分

默认实现了本地文件系统存储和MinIO对象存储, 如果您需要进行存储方式自定义, 可通过实现`AttachmentStorage`接口实现:

```java
@Component
public class MyAttachmentStorage implements AttachmentStorage {
  @Override
  public Attachment upload(MultipartFile multipartFile) throws Exception {
    // 上传逻辑...
  }
    
  @Override
  public void download(Attachment attachment, HttpServletResponse response) throws Exception {
    // 下载逻辑...
  }
    
  @Override
  public void remove(Attachment attachment) throws Exception {
    // 删除逻辑...
  }
}
```

### 自定义附件工厂

在Spring Boot中, 一般使用`MultipartFile`接收上传文件

模块提供了工厂接口`AttachmentFactory`, 用于根据`MultipartFile`生成`Attachment`, 默认实现为`DefaultAttachmentFactory`

如果您对附件部分参数有定制需求, 无需重写`AttachmentService`或`AttachmentStorage`, 创建自定义工厂即可:

```java
@Component
public class MyAttachmentFactory implements AttachmentFactory {
  @Override
  public Attachment create(MultipartFile multipartFile) {
    // 生成附件逻辑...
  }
}
```



