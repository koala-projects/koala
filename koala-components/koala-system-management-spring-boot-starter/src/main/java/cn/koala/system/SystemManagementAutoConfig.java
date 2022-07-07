package cn.koala.system;

import cn.koala.system.mybatis.JwtAuthenticationUserConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Houtaroy
 */
@Configuration
@Import({ApiConfig.class, ServiceConfig.class, SetAuthenticationUserConverterRunner.class})
@MapperScan(basePackages = "cn.koala.system.mybatis")
public class SystemManagementAutoConfig {

  /**
   * 用户Jwt上下文定制器的bean
   *
   * @return 用户Jwt上下文定制器
   */
  @Bean
  public UserJwtEncodeContextCustomizer userJwtEncodeContextCustomizer() {
    return new UserJwtEncodeContextCustomizer();
  }

  /**
   * 认证信息用户转换器的bean
   *
   * @return 认证信息用户转换器
   */
  @Bean
  @ConditionalOnMissingBean
  public AuthenticationUserConverter authenticationUserConverter() {
    return new JwtAuthenticationUserConverter();
  }
}
