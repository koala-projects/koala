package cn.koala.persist.autoconfigure;

import cn.koala.persist.CrudService;
import cn.koala.persist.CrudServiceRegistry;
import cn.koala.persist.support.DefaultCrudServiceRegistry;
import cn.koala.validation.DefaultableMessageSourceLocator;
import cn.koala.validation.MessageSourceLocator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

/**
 * 持久化自动配置
 *
 * @author Houtaroy
 */
@Configuration
@EnableConfigurationProperties(InitializerProperties.class)
@Import({InitializerAutoConfiguration.class, EntityListenerAutoConfiguration.class})
public class PersistAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public CrudServiceRegistry crudServiceRegistry(List<CrudService<?, ?>> services) {
    return new DefaultCrudServiceRegistry(services);
  }

  @Bean
  @ConditionalOnMissingBean(name = "persistMessageSourceLocator")
  public MessageSourceLocator persistMessageSourceLocator() {
    return new DefaultableMessageSourceLocator("persist");
  }
}
