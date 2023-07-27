package cn.koala.attachment.autoconfigure;

import cn.koala.attachment.storage.AttachmentStorage;
import cn.koala.attachment.storage.support.MinioAttachmentStorage;
import io.minio.MinioClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO: 修改类描述
 *
 * @author Houtaroy
 */
@Configuration
@ConditionalOnProperty(value = "koala.attachment.type", havingValue = AttachmentProperties.TYPE_MINIO)
@ConditionalOnClass(name = "io.minio.MinioClient")
public class MinioAttachmentStorageAutoConfiguration {

  @Bean
  @ConditionalOnBean(MinioClient.class)
  @ConditionalOnMissingBean
  public AttachmentStorage minIOAttachmentStorage(MinioClient client) throws Exception {
    return new MinioAttachmentStorage(client);
  }
}
