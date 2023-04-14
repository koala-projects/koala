package cn.koala.validation.autoconfigure;

import cn.koala.validation.MessageSourceLocator;
import org.springframework.boot.autoconfigure.validation.ValidationConfigurationCustomizer;
import org.springframework.context.MessageSource;
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
  public ValidationConfigurationCustomizer messageSourceCustomizer(MessageSource parent,
                                                                   List<MessageSourceLocator> locators) {
    return new MessageSourceCustomizer(parent, locators);
  }
}
