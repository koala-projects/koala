package cn.koala.persist.autoconfigure;

import cn.koala.persist.listener.AuditingEntityListener;
import cn.koala.persist.listener.EntityListener;
import cn.koala.persist.listener.StatefulEntityListener;
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
  public CrudServicePostProcessor crudServicePostProcessor(List<EntityListener> listeners) {
    return new CrudServicePostProcessor(listeners);
  }
}