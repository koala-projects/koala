package cn.koala.attachment.autoconfigure;

import cn.koala.attachment.storage.AttachmentStorage;
import cn.koala.attachment.storage.support.LocalFileAttachmentStorage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 本地文件附件存储器自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@ConditionalOnProperty(value = "koala.attachment.type", havingValue = AttachmentProperties.TYPE_LOCAL)
public class LocalFileAttachmentStorageAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "localAttachmentStorage")
  public AttachmentStorage localAttachmentStorage(AttachmentProperties properties) {
    return new LocalFileAttachmentStorage(properties.getRoot());
  }
}
