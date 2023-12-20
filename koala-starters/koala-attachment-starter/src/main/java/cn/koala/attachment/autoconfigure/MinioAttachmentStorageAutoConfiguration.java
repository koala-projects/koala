package cn.koala.attachment.autoconfigure;

import cn.koala.attachment.domain.AttachmentStorage;
import cn.koala.attachment.domain.AttachmentStoragePathStrategy;
import cn.koala.attachment.domain.LocalDateTimeAttachmentStoragePathStrategy;
import cn.koala.attachment.domain.MinioAttachmentStorage;
import cn.koala.minio.autoconfigure.MinioProperties;
import io.minio.MinioClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Minio附件存储器自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@ConditionalOnClass(name = "cn.koala.minio.autoconfigure.MinioProperties")
@ConditionalOnProperty(value = "koala.attachment.type", havingValue = AttachmentProperties.TYPE_MINIO)
public class MinioAttachmentStorageAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public AttachmentStoragePathStrategy attachmentStoragePathStrategy() {
    return new LocalDateTimeAttachmentStoragePathStrategy();
  }

  @Bean
  @ConditionalOnMissingBean(name = "minIOAttachmentStorage")
  public AttachmentStorage minIOAttachmentStorage(
    MinioClient client,
    MinioProperties minioProperties,
    AttachmentProperties attachmentProperties,
    AttachmentStoragePathStrategy attachmentStoragePathStrategy
  ) throws Exception {

    return new MinioAttachmentStorage(
      client,
      minioProperties.getEndpoint(),
      attachmentProperties.getBucket(),
      attachmentStoragePathStrategy
    );
  }
}
