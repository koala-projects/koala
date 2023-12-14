package cn.koala.code.autoconfigure;

import cn.koala.codegen.context.type.JdbcJavaTypeMapping;
import cn.koala.codegen.context.type.JdbcJsonTypeMapping;
import cn.koala.codegen.context.type.JdbcTypeMapping;
import cn.koala.codegen.context.type.JdbcTypeScriptTypeMapping;
import cn.koala.codegen.context.type.JdbcVbenComponentTypeMapping;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Houtaroy
 */
@Configuration
public class JdbcTypeMappingAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "jdbcJavaTypeMapping")
  public JdbcTypeMapping jdbcJavaTypeMapping() {
    return new JdbcJavaTypeMapping();
  }

  @Bean
  @ConditionalOnMissingBean(name = "jdbcJsonTypeMapping")
  public JdbcTypeMapping jdbcJsonTypeMapping() {
    return new JdbcJsonTypeMapping();
  }

  @Bean
  @ConditionalOnMissingBean(name = "jdbcTypeScriptTypeMapping")
  public JdbcTypeMapping jdbcTypeScriptTypeMapping() {
    return new JdbcTypeScriptTypeMapping();
  }

  @Bean
  @ConditionalOnMissingBean(name = "jdbcVbenComponentTypeMapping")
  public JdbcTypeMapping jdbcVbenComponentTypeMapping() {
    return new JdbcVbenComponentTypeMapping();
  }
}
