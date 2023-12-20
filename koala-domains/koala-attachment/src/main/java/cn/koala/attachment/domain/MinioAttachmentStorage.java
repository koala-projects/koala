package cn.koala.attachment.domain;

import cn.koala.util.Assert;
import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.SetBucketPolicyArgs;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * Minio附件存储器
 *
 * @author Houtaroy
 */
public class MinioAttachmentStorage implements AttachmentStorage {

  private static final String POLICY_TEMPLATE = """
        {
            "Version": "2012-10-17",
            "Statement": [
                {
                    "Effect": "Allow",
                    "Principal": {
                        "AWS": [
                            "*"
                        ]
                    },
                    "Action": [
                        "s3:GetObject"
                    ],
                    "Resource": [
                        "arn:aws:s3:::%s/*"
                    ]
                }
            ]
        }
    """;

  private static final String URI_TEMPLATE = "%s/%s/%s";

  private final MinioClient client;

  private final String endpoint;

  private final String bucket;

  private final AttachmentStoragePathStrategy storagePathStrategy;

  public MinioAttachmentStorage(MinioClient client, String endpoint, String bucket) throws Exception {
    this(client, endpoint, bucket, new LocalDateTimeAttachmentStoragePathStrategy());
  }

  public MinioAttachmentStorage(MinioClient client, String endpoint, String bucket,
                                AttachmentStoragePathStrategy storagePathStrategy) throws Exception {

    Assert.notNull(client, "MinIO客户端不能为空");
    Assert.hasText(bucket, "存储桶名称不能为空");
    Assert.notNull(storagePathStrategy, "附件存储路径策略不能为空");
    this.client = client;
    this.endpoint = endpoint;
    this.bucket = bucket;
    this.storagePathStrategy = storagePathStrategy;
    createBucketIfAbsent();
  }

  private void createBucketIfAbsent() throws Exception {
    if (client.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())) {
      return;
    }
    client.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
    client.setBucketPolicy(
      SetBucketPolicyArgs.builder().bucket(bucket).config(POLICY_TEMPLATE.formatted(bucket)).build()
    );
  }

  @Override
  public Attachment put(MultipartFile multipartFile) throws Exception {
    var storagePath = storagePathStrategy.getStoragePath(multipartFile);
    client.putObject(
      PutObjectArgs.builder()
        .bucket(bucket)
        .object(storagePath)
        .contentType(multipartFile.getContentType())
        .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
        .build()
    );
    return AttachmentEntity.builder()
      .originalFilename(multipartFile.getOriginalFilename())
      .contentType(multipartFile.getContentType())
      .size(multipartFile.getSize())
      .storagePath(storagePath)
      .storageUri(URI_TEMPLATE.formatted(endpoint, bucket, storagePath))
      .build();
  }

  @Override
  public InputStream get(Attachment attachment) throws Exception {
    return client.getObject(
      GetObjectArgs.builder()
        .bucket(bucket)
        .object(attachment.getStoragePath())
        .build()
    );
  }

  @Override
  public void remove(Attachment attachment) throws Exception {
    client.removeObject(
      RemoveObjectArgs.builder()
        .bucket(bucket)
        .object(attachment.getStoragePath())
        .build()
    );
  }
}
