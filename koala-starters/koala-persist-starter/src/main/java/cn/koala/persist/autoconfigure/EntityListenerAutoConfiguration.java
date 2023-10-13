package cn.koala.persist.autoconfigure;

import cn.koala.persist.EntityListenerAspect;
import cn.koala.persist.EntityListenerFactory;
import cn.koala.persist.EntityListenerMethodAnnotationFactory;
import cn.koala.persist.EntityListenerMethodFactory;
import cn.koala.persist.SystemEntityListener;
import cn.koala.persist.support.CrudServiceEntityListenerMethodAnnotationFactory;
import cn.koala.persist.support.DefaultAuditingEntityListener;
import cn.koala.persist.support.SimpleEntityListenerMethodFactory;
import cn.koala.persist.support.SpringBeanEntityListenerFactory;
import cn.koala.persist.support.StatefulEntityListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Optional;

/**
 * 实体监听器自动配置类
 *
 * @author Houtaroy
 */
@Configuration
public class EntityListenerAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "statefulEntityListener")
  public SystemEntityListener statefulEntityListener() {
    return new StatefulEntityListener();
  }

  @Bean
  @ConditionalOnMissingBean(name = "auditingEntityListener")
  public SystemEntityListener auditingEntityListener(Optional<AuditorAware<Long>> auditorAware) {
    return new DefaultAuditingEntityListener(auditorAware.orElse(null));
  }

  @Bean
  @ConditionalOnMissingBean
  public EntityListenerFactory entityListenerFactory() {
    return new SpringBeanEntityListenerFactory();
  }

  @Bean
  @ConditionalOnMissingBean
  public EntityListenerMethodFactory entityListenerMethodFactory() {
    return new SimpleEntityListenerMethodFactory();
  }

  @Bean
  @ConditionalOnMissingBean
  public EntityListenerMethodAnnotationFactory entityListenerMethodAnnotationFactory() {
    return new CrudServiceEntityListenerMethodAnnotationFactory();
  }

  @Bean
  @ConditionalOnMissingBean
  public EntityListenerAspect entityListenerAspect(EntityListenerFactory factory,
                                                   EntityListenerMethodFactory methodFactory,
                                                   EntityListenerMethodAnnotationFactory annotationFactory,
                                                   PlatformTransactionManager transactionManager) {

    return new EntityListenerAspect(factory, methodFactory, annotationFactory, transactionManager);
  }
}
