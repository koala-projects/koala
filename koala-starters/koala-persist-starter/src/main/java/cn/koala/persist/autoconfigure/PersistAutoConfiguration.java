package cn.koala.persist.autoconfigure;

import cn.koala.persist.listener.AuditingEntityListener;
import cn.koala.persist.listener.EntityListener;
import cn.koala.persist.listener.StatefulEntityListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

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
  public AuditingEntityListener auditingEntityListener() {
    return new AuditingEntityListener(Optional::empty);
  }

  @Bean
  @ConditionalOnMissingBean
  public CrudServicePostProcessor crudServicePostProcessor(List<EntityListener> listeners) {
    return new CrudServicePostProcessor(listeners);
  }
}
