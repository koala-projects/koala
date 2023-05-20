package cn.koala.persist.autoconfigure;

import cn.koala.persist.initializer.DataSourceInitializer;
import cn.koala.persist.initializer.support.CompositeInitializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.List;

/**
 * 初始化器自动配置类
 *
 * @author Houtaroy
 */
@Configuration
public class InitializerAutoConfiguration {

  @Bean
  @ConditionalOnBean(DataSource.class)
  public CompositeInitializer compositeInitializer(DataSource dataSource, InitializerProperties properties,
                                                   List<DataSourceInitializer> initializers) {
    return new CompositeInitializer(dataSource, properties, initializers);
  }
}
