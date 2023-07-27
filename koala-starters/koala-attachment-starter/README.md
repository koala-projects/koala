# Koala Attachment Starter

考拉附件启动模块, 实现附件上传和下载功能

## 快速开始

### 初始化

可通过如下两种方式初始化数据库:

1. 配置开启模块初始化器:

```yaml
koala:
  persist:
    initializer:
      attachment: true
```

2. 执行[脚本目录](../../koala-domains/koala-attachment/src/main/resources/database/attachment)下的结构脚本 `schema.sql` 和数据脚本 `data.sql`

### 配置

本地文件存储:

```yaml
koala:
  attachment:
    type: local
    root: /tmp/attachment
```

Minio对象存储:

```yaml
koala:
  attachment:
    type: minio
  minio:
    endpoint: http://127.0.0.1:9000
    access-key: minioadmin
    secret-key: minioadmin
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

### 自定义附件存储

模块将附件存储抽象出来, 与持久化逻辑进行拆分

默认实现了本地文件存储和Minio对象存储, 如果您需要进行存储方式自定义, 可通过实现`AttachmentStorage`接口实现:

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



