package cn.koala.persist.autoconfigure;

import cn.koala.persist.CrudService;
import cn.koala.persist.CrudServiceRegistry;
import cn.koala.persist.listener.EntityListener;
import cn.koala.persist.listener.EntityListenerAspect;
import cn.koala.persist.listener.EntityListenerRegistry;
import cn.koala.persist.listener.support.AuditingEntityListener;
import cn.koala.persist.listener.support.DefaultEntityListenerRegistry;
import cn.koala.persist.listener.support.StatefulEntityListener;
import cn.koala.persist.support.DefaultCrudServiceRegistry;
import cn.koala.validation.DefaultableMessageSourceLocator;
import cn.koala.validation.MessageSourceLocator;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.List;

/**
 * 持久化自动配置
 *
 * @author Houtaroy
 */
@Configuration
public class PersistAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public CrudServiceRegistry crudServiceRegistry(List<CrudService<?, ?>> services) {
    return new DefaultCrudServiceRegistry(services);
  }

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

  @Bean
  @ConditionalOnMissingBean(name = "persistMessageSourceLocator")
  public MessageSourceLocator persistMessageSourceLocator() {
    return new DefaultableMessageSourceLocator("validation.persist");
  }
}
