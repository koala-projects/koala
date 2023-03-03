package cn.koala.code.autoconfigure;

import cn.koala.code.apis.GeneratorApi;
import cn.koala.code.apis.GeneratorApiImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 代码生成器自动配置类
 *
 * @author Houtaroy
 */
@Configuration
public class CodeGeneratorAutoConfiguration {
  @Bean
  @ConditionalOnMissingBean
  public GeneratorApi buildApi(DataSource dataSource) {
    return new GeneratorApiImpl(dataSource);
  }
}
