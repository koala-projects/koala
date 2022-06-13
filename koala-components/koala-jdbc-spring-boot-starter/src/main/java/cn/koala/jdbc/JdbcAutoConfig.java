package cn.koala.jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Houtaroy
 */
@Configuration
public class JdbcAutoConfig {
  /**
   * Jdbc接口的bean
   *
   * @return Jdbc接口
   */
  @Bean
  public JdbcApi jdbcApi() {
    return new JdbcApiImpl();
  }
}
