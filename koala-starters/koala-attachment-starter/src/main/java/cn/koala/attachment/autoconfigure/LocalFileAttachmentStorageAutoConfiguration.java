package cn.koala.attachment.autoconfigure;

import cn.koala.attachment.domain.AttachmentStorage;
import cn.koala.attachment.domain.AttachmentStoragePathStrategy;
import cn.koala.attachment.domain.LocalDateTimeAttachmentStoragePathStrategy;
import cn.koala.attachment.domain.LocalFileAttachmentStorage;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 本地文件附件存储器自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@ConditionalOnProperty(
  value = "koala.attachment.type",
  havingValue = AttachmentProperties.TYPE_LOCAL,
  matchIfMissing = true
)
@RequiredArgsConstructor
public class LocalFileAttachmentStorageAutoConfiguration implements WebMvcConfigurer {

  private final AttachmentProperties properties;

  @Bean
  @ConditionalOnMissingBean
  public AttachmentStoragePathStrategy attachmentStoragePathStrategy() {
    return new LocalDateTimeAttachmentStoragePathStrategy();
  }

  @Bean
  @ConditionalOnMissingBean(name = "localFileAttachmentStorage")
  public AttachmentStorage localFileAttachmentStorage(AttachmentStoragePathStrategy attachmentStoragePathStrategy) {

    return new LocalFileAttachmentStorage(properties.getRoot(), properties.getEndpoint(), attachmentStoragePathStrategy);
  }

  @Override
  public void addResourceHandlers(@NotNull ResourceHandlerRegistry registry) {
    registry.addResourceHandler("%s/**".formatted(properties.getEndpoint()))
      .addResourceLocations("file:%s".formatted(properties.getRoot()));
  }
}
