package cn.koala.attachment.autoconfigure;

import cn.koala.attachment.api.AttachmentApi;
import cn.koala.attachment.api.DefaultAttachmentApi;
import cn.koala.attachment.domain.AttachmentStorage;
import cn.koala.attachment.repository.AttachmentRepository;
import cn.koala.attachment.service.AttachmentService;
import cn.koala.attachment.service.DefaultAttachmentService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 附件自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@Import({
  LocalFileAttachmentStorageAutoConfiguration.class,
  MinioAttachmentStorageAutoConfiguration.class,
  AttachmentPermissionAutoConfiguration.class
})
@EnableConfigurationProperties(AttachmentProperties.class)
@MapperScan("cn.koala.attachment.repository")
public class AttachmentAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnBean(AttachmentStorage.class)
  public AttachmentService attachmentService(AttachmentRepository repository, AttachmentStorage storage) {
    return new DefaultAttachmentService(repository, storage);
  }

  @Bean
  @ConditionalOnMissingBean
  public AttachmentApi attachmentApi(AttachmentService service) {
    return new DefaultAttachmentApi(service);
  }
}
