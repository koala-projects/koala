package cn.koala.persist.autoconfigure;

import cn.koala.persist.AnnotationResolver;
import cn.koala.persist.AroundEntityListenerAspect;
import cn.koala.persist.CrudMethod;
import cn.koala.persist.EntityListenerFactory;
import cn.koala.persist.EntityListenerMethodFactory;
import cn.koala.persist.SystemEntityListener;
import cn.koala.persist.support.AuditingEntityListener;
import cn.koala.persist.support.CrudMethodEntityClassResolver;
import cn.koala.persist.support.MethodAroundEntityListener;
import cn.koala.persist.support.NameMappingAnnotationResolver;
import cn.koala.persist.support.SimpleEntityListenerMethodFactory;
import cn.koala.persist.support.SimpleMethodEntityListenerFactory;
import cn.koala.persist.support.SimpleMethodEntityListenerMethodFactory;
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
@Configuration
public class EntityListenerAutoConfiguration {

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
  @Order(3200)
  @ConditionalOnMissingBean(name = "crudMethodEntityListenerAspect")
  public AroundEntityListenerAspect crudMethodEntityListenerAspect(
    PlatformTransactionManager transactionManager, EntityListenerFactory entityListenerFactory,
    EntityListenerMethodFactory entityListenerMethodFactory) {

    AnnotationResolver jpaPreAnnotationResolver =
      new NameMappingAnnotationResolver(CrudMethod.JPA_PRE_ANNOTATION_MAPPING);
    AnnotationResolver jpaPostAnnotationResolver =
      new NameMappingAnnotationResolver(CrudMethod.JPA_POST_ANNOTATION_MAPPING);

    return new AroundEntityListenerAspect(
      new MethodAroundEntityListener(
        transactionManager,
        new SimpleMethodEntityListenerFactory(new CrudMethodEntityClassResolver(), entityListenerFactory),
        new SimpleMethodEntityListenerMethodFactory(jpaPreAnnotationResolver, entityListenerMethodFactory),
        new SimpleMethodEntityListenerMethodFactory(jpaPostAnnotationResolver, entityListenerMethodFactory)
      )
    );
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
