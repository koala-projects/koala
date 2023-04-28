package cn.koala.attachment.storage;

import cn.koala.attachment.Attachment;
import cn.koala.attachment.factory.AttachmentFactory;
import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * TODO: 修改类描述
 *
 * @author Houtaroy
 */
public class MinIOAttachmentStorage extends AbstractAttachmentStorage {

  public static final String DEFAULT_BUCKET = "attachment";
  protected final MinioClient client;
  protected final String bucket = DEFAULT_BUCKET;

  public MinIOAttachmentStorage(AttachmentFactory factory, MinioClient minioClient) {
    super(factory);
    this.client = minioClient;
  }

  @Override
  public void doUpload(MultipartFile multipartFile, Attachment attachment) throws Exception {
    createBucketIfAbsent();
    client.putObject(
      PutObjectArgs.builder()
        .bucket(bucket)
        .object(attachment.getStoragePath())
        .contentType(attachment.getContentType())
        .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
        .build()
    );
  }

  protected void createBucketIfAbsent() throws Exception {
    if (client.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())) {
      return;
    }
    client.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
  }

  @Override
  protected InputStream doDownload(Attachment attachment) throws Exception {
    return client.getObject(
      GetObjectArgs.builder()
        .bucket(bucket)
        .object(attachment.getStoragePath())
        .build());
  }
}
