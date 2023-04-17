package cn.koala.validation.autoconfigure;

import cn.koala.validation.MessageSourceLocator;
import cn.koala.validation.SimpleMessageSourceLocator;
import org.springframework.beans.factory.ObjectProvider;
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
  public ValidationConfigurationCustomizer messageSourceCustomizer(ObjectProvider<MessageSourceProperties> properties,
                                                                   List<MessageSourceLocator> locators) {
    properties.ifAvailable(data -> locators.add(0, new SimpleMessageSourceLocator(data.getBasename())));
    return new ValidationMessageSourceCustomizer(locators);
  }
}
