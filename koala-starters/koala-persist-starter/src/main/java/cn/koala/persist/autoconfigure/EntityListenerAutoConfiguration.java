package cn.koala.persist.autoconfigure;

import cn.koala.persist.CrudServiceEntityListenerAspect;
import cn.koala.persist.EntityListenerFactory;
import cn.koala.persist.SystemEntityListener;
import cn.koala.persist.support.AuditingEntityListener;
import cn.koala.persist.support.SpringBeanEntityListenerFactory;
import cn.koala.persist.support.StatefulEntityListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.AuditorAware;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Optional;

/**
 * 实体监听器自动配置类
 *
 * @author Houtaroy
 */
@Deprecated
@Configuration
public class EntityListenerAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public EntityListenerFactory entityListenerFactory() {
    return new SpringBeanEntityListenerFactory();
  }

  @Bean
  @ConditionalOnMissingBean
  public CrudServiceEntityListenerAspect crudServiceEntityListenerAspect(PlatformTransactionManager transactionManager,
                                                                         EntityListenerFactory entityListenerFactory) {

    return new CrudServiceEntityListenerAspect(transactionManager, entityListenerFactory);
  }

  @Bean
  @ConditionalOnMissingBean(name = "statefulEntityListener")
  public SystemEntityListener statefulEntityListener() {
    return new StatefulEntityListener();
  }

  @Bean
  @Order(2000)
  @ConditionalOnMissingBean(name = "auditingEntityListener")
  public SystemEntityListener auditingEntityListener(Optional<AuditorAware<Long>> auditorAware) {
    return new AuditingEntityListener<>(auditorAware.orElse(null));
  }
}
