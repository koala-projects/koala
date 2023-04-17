package cn.koala.validation.autoconfigure;

import cn.koala.validation.MessageSourceLocator;
import cn.koala.validation.SimpleMessageSourceLocator;
import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.boot.autoconfigure.validation.ValidationConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 校验自动配置类
 *
 * @author Houtaroy
 */
@Configuration
public class ValidationAutoConfiguration {

  @Bean
  public ValidationConfigurationCustomizer messageSourceCustomizer(MessageSourceProperties properties,
                                                                   List<MessageSourceLocator> locators) {
    locators.add(0, new SimpleMessageSourceLocator(properties.getBasename()));
    return new ValidationMessageSourceCustomizer(locators);
  }
}
