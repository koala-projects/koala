# Koala Attachment Starter

考拉附件启动模块, 实现附件上传和下载功能

## 快速开始

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

### 自定义附件工厂

在Spring Boot中, 一般使用`MultipartFile`接收上传文件

模块提供了工厂接口`AttachmentFactory`, 用于根据`MultipartFile`生成`Attachment`, 默认实现为`DefaultAttachmentFactory`

如果您对附件部分参数有定制需求, 无需重写`AttachmentService`, 创建自定义工厂即可:

```java
@Component
public class MyAttachmentFactory implements AttachmentFactory {
  @Override
  public Attachment create(MultipartFile multipartFile) {
    // 生成附件逻辑...
  }
}
```



