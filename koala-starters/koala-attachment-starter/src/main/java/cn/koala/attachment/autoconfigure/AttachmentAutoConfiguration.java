package cn.koala.attachment.autoconfigure;

import cn.koala.attachment.AttachmentApi;
import cn.koala.attachment.DefaultAttachmentApi;
import cn.koala.attachment.AttachmentFactory;
import cn.koala.attachment.AttachmentListener;
import cn.koala.attachment.AttachmentProperties;
import cn.koala.attachment.AttachmentService;
import cn.koala.attachment.DefaultAttachmentFactory;
import cn.koala.attachment.DefaultAttachmentService;
import cn.koala.attachment.repository.AttachmentRepository;
import cn.koala.persist.listener.EntityListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 附件自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@EnableConfigurationProperties(AttachmentProperties.class)
@MapperScan("cn.koala.attachment.repository")
public class AttachmentAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public AttachmentFactory attachmentFactory(AttachmentProperties attachmentProperties) {
    return new DefaultAttachmentFactory(attachmentProperties);
  }

  @Bean
  @ConditionalOnMissingBean(name = "attachmentService")
  public AttachmentService attachmentService(AttachmentRepository attachmentRepository,
                                             AttachmentFactory attachmentFactory) {
    return new DefaultAttachmentService(attachmentRepository, attachmentFactory);
  }

  @Bean
  @ConditionalOnMissingBean(name = "attachmentListener")
  public EntityListener attachmentListener() {
    return new AttachmentListener();
  }

  @Bean
  @ConditionalOnMissingBean
  public AttachmentApi attachmentApi(AttachmentService attachmentService) {
    return new DefaultAttachmentApi(attachmentService);
  }
}
