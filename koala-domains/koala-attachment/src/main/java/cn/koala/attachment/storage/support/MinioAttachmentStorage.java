package cn.koala.attachment.storage.support;

import cn.koala.attachment.domain.Attachment;
import cn.koala.attachment.storage.AttachmentFactory;
import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * Minio附件存储器
 *
 * @author Houtaroy
 */
public class MinioAttachmentStorage extends AbstractAttachmentStorage {

  public static final String DEFAULT_BUCKET = "attachment";
  protected final MinioClient client;
  protected final String bucket;

  public MinioAttachmentStorage(MinioClient client) throws Exception {
    this(new DefaultAttachmentFactory(), client, DEFAULT_BUCKET);
  }

  public MinioAttachmentStorage(AttachmentFactory factory, MinioClient minioClient, String bucket) throws Exception {
    super(factory);
    this.client = minioClient;
    this.bucket = bucket;
    this.createBucketIfAbsent();
  }

  private void createBucketIfAbsent() throws Exception {
    if (client.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())) {
      return;
    }
    client.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
  }

  @Override
  public Attachment upload(MultipartFile multipartFile) throws Exception {
    createBucketIfAbsent();
    Attachment result = this.toAttachment(multipartFile);
    client.putObject(
      PutObjectArgs.builder()
        .bucket(bucket)
        .object(result.getStoragePath())
        .contentType(result.getContentType())
        .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
        .build()
    );
    return result;
  }

  @Override
  public InputStream download(Attachment attachment) throws Exception {
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
