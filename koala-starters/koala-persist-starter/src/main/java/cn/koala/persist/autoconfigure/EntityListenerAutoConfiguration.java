package cn.koala.persist.autoconfigure;

import cn.koala.persist.listener.EntityListener;
import cn.koala.persist.listener.EntityListenerAspect;
import cn.koala.persist.listener.EntityListenerRegistry;
import cn.koala.persist.listener.support.AuditingEntityListener;
import cn.koala.persist.listener.support.DefaultEntityListenerRegistry;
import cn.koala.persist.listener.support.StatefulEntityListener;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.List;

/**
 * 实体监听器自动配置类
 *
 * @author Houtaroy
 */
@Configuration
public class EntityListenerAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public StatefulEntityListener statefulEntityListener() {
    return new StatefulEntityListener();
  }

  @Bean
  @ConditionalOnMissingBean
  public AuditingEntityListener auditingEntityListener(ObjectProvider<AuditorAware<?>> auditorAware) {
    return new AuditingEntityListener(auditorAware);
  }

  @Bean
  @ConditionalOnMissingBean
  public EntityListenerRegistry entityListenerRegistry(List<EntityListener> listeners) {
    return new DefaultEntityListenerRegistry(listeners);
  }

  @Bean
  @ConditionalOnMissingBean
  public EntityListenerAspect entityListenerAspect(EntityListenerRegistry registry) {
    return new EntityListenerAspect(registry);
  }
}