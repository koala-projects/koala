package cn.koala.code.autoconfigure;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc配置
 *
 * @author Houtaroy
 */
@Configuration
@RequiredArgsConstructor
public class CodeGenWebMvcConfigurer implements WebMvcConfigurer {

  private final CodeGenProperties properties;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/api/code/download/**")
      .addResourceLocations("file:" + properties.getDownloadPath());
  }
}
