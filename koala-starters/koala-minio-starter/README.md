# Koala MinIO Starter

考拉MinIO启动模块, 集成MinIO对象存储功能

## 快速开始

### 配置

```yaml
koala:
  minio:
    endpoint: http://127.0.0.1:9000
    access-key: minioadmin
    secret-key: minioadmin
```

### 自动注入客户端

```java

@RequiredArgsConstructor
public class MinIOService {
  // 同步客户端
  private final MinioClient minioClient;
  // 异步客户端
  private final MinioAsyncClient minioAsyncClient;
}
```

客户端操作请参照 [MinIO Client SDK for Java](https://github.com/minio/minio-java)
