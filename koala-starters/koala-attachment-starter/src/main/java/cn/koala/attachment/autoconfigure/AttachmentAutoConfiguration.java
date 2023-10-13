package cn.koala.attachment.autoconfigure;

import cn.koala.attachment.AttachmentApi;
import cn.koala.attachment.AttachmentEntityListener;
import cn.koala.attachment.AttachmentService;
import cn.koala.attachment.repository.AttachmentRepository;
import cn.koala.attachment.storage.AttachmentStorage;
import cn.koala.attachment.support.DefaultAttachmentApi;
import cn.koala.attachment.support.DefaultAttachmentService;
import org.mybatis.spring.annotation.MapperScan;
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
@Import({LocalFileAttachmentStorageAutoConfiguration.class, MinioAttachmentStorageAutoConfiguration.class,
  AttachmentPermissionAutoConfiguration.class})
@EnableConfigurationProperties(AttachmentProperties.class)
@MapperScan("cn.koala.attachment.repository")
public class AttachmentAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "attachmentService")
  public AttachmentService attachmentService(AttachmentRepository repository) {
    return new DefaultAttachmentService(repository);
  }

  @Bean
  @ConditionalOnMissingBean
  public AttachmentApi attachmentApi(AttachmentService service, AttachmentStorage storage) {
    return new DefaultAttachmentApi(service, storage);
  }

  @Bean
  @ConditionalOnMissingBean(name = "attachmentEntityListener")
  public AttachmentEntityListener attachmentEntityListener(AttachmentRepository repository, AttachmentStorage storage) {
    return new AttachmentEntityListener(repository, storage);
  }
}
