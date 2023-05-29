package cn.koala.system.autoconfigure;

import cn.koala.validation.DefaultableMessageSourceLocator;
import cn.koala.validation.MessageSourceLocator;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * 校验自动配置类
 *
 * @author Houtaroy
 */
@AutoConfiguration
public class ValidationAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "systemMessageSourceLocator")
  public MessageSourceLocator systemMessageSourceLocator() {
    return new DefaultableMessageSourceLocator(
      "department", "dictionary", "dictionary-item", "permission", "role", "user"
    );
  }
}
