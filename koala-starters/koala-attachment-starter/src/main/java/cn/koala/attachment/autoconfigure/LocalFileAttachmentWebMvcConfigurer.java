package cn.koala.attachment.autoconfigure;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 本地附件资源处理器配置类
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
public class LocalFileAttachmentWebMvcConfigurer implements WebMvcConfigurer {

  private final AttachmentProperties properties;

  @Override
  public void addResourceHandlers(@NotNull ResourceHandlerRegistry registry) {
    registry.addResourceHandler("%s/**".formatted(properties.getEndpoint()))
      .addResourceLocations("file:%s".formatted(properties.getRoot()));
  }
}
