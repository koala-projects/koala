package cn.koala.web;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * @author Houtaroy
 */
@Configuration
public class WebAutoConfig {

  /**
   * Jackson2ObjectMapper定制器的bean
   *
   * @return Jackson2ObjectMapper定制器
   */
  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
    return builder -> builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer())
      .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer());
  }

  /**
   * Rest风格异常处理器的bean
   *
   * @return Rest风格异常处理器
   */
  @Bean
  @ConditionalOnMissingBean
  public RestExceptionHandler restExceptionHandler() {
    return new RestExceptionHandler();
  }
}
