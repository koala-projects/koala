package cn.koala.attachment.autoconfigure;

import cn.koala.attachment.storage.AttachmentStorage;
import cn.koala.attachment.storage.support.MinioAttachmentStorage;
import cn.koala.minio.autoconfigure.MinioAutoConfiguration;
import io.minio.MinioClient;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
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
@AutoConfigureAfter(MinioAutoConfiguration.class)
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
