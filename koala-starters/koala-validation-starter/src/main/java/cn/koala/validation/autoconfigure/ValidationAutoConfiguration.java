package cn.koala.validation.autoconfigure;

import cn.koala.validation.MessageSourceLocator;
import jakarta.validation.Validator;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.validation.ValidationConfigurationCustomizer;
import org.springframework.boot.validation.MessageInterpolatorFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.List;

/**
 * 校验自动配置类
 *
 * @author Houtaroy
 */
@Configuration
public class ValidationAutoConfiguration {

  @Bean
  public Validator validator(ObjectProvider<ValidationConfigurationCustomizer> customizers) {
    LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
    MessageInterpolatorFactory interpolatorFactory = new MessageInterpolatorFactory();
    factoryBean.setMessageInterpolator(interpolatorFactory.getObject());
    factoryBean.setConfigurationInitializer((configuration) -> customizers.orderedStream()
      .forEach((customizer) -> customizer.customize(configuration)));
    return factoryBean;
  }

  @Bean
  public ValidationConfigurationCustomizer messageSourceCustomizer(MessageSource parent,
                                                                   List<MessageSourceLocator> locators) {
    return new MessageSourceCustomizer(parent, locators);
  }
}
