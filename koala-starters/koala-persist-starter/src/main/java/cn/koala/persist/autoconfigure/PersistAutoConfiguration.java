package cn.koala.persist.autoconfigure;

import cn.koala.persist.CrudService;
import cn.koala.persist.CrudServiceManager;
import cn.koala.persist.EntityListener;
import cn.koala.persist.EntityListenerAspect;
import cn.koala.persist.EntityListenerManager;
import cn.koala.persist.support.AuditingEntityListener;
import cn.koala.persist.support.DefaultCrudServiceManager;
import cn.koala.persist.support.DefaultEntityListenerManager;
import cn.koala.persist.support.StatefulEntityListener;
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
  public CrudServiceManager crudServiceManager(List<CrudService<?, ?>> services) {
    return new DefaultCrudServiceManager(services);
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
  public EntityListenerManager entityListenerManager(List<EntityListener> listeners) {
    return new DefaultEntityListenerManager(listeners);
  }

  @Bean
  @ConditionalOnMissingBean
  public EntityListenerAspect entityListenerAspect(EntityListenerManager manager) {
    return new EntityListenerAspect(manager);
  }
}
