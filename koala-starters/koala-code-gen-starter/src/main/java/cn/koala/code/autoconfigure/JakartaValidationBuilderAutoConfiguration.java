package cn.koala.code.autoconfigure;

import cn.koala.codegen.context.validation.JakartaDigitsValidationBuilder;
import cn.koala.codegen.context.validation.JakartaMaxValidationBuilder;
import cn.koala.codegen.context.validation.JakartaNotNullValidationBuilder;
import cn.koala.codegen.context.validation.JakartaSizeValidationBuilder;
import cn.koala.codegen.context.validation.JakartaValidationBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Houtaroy
 */
@Configuration
public class JakartaValidationBuilderAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "jakartaDigitsValidationBuilder")
  public JakartaValidationBuilder jakartaDigitsValidationBuilder() {
    return new JakartaDigitsValidationBuilder();
  }

  @Bean
  @ConditionalOnMissingBean(name = "jakartaMaxValidationBuilder")
  public JakartaValidationBuilder jakartaMaxValidationBuilder() {
    return new JakartaMaxValidationBuilder();
  }

  @Bean
  @ConditionalOnMissingBean(name = "jakartaNotNullValidationBuilder")
  public JakartaValidationBuilder jakartaNotNullValidationBuilder() {
    return new JakartaNotNullValidationBuilder();
  }

  @Bean
  @ConditionalOnMissingBean(name = "jakartaSizeValidationBuilder")
  public JakartaValidationBuilder jakartaSizeValidationBuilder() {
    return new JakartaSizeValidationBuilder();
  }
}
