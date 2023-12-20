package cn.koala.mybatis.autoconfigure;

import cn.koala.mybatis.listener.AuditingGlobalEntityListener;
import cn.koala.mybatis.listener.BeanEntityListenerFactory;
import cn.koala.mybatis.listener.CrudServiceEntityListenerAspect;
import cn.koala.mybatis.listener.EnableableGlobalEntityListener;
import cn.koala.mybatis.listener.EntityListenerFactory;
import cn.koala.mybatis.listener.GlobalEntityListener;
import cn.koala.mybatis.listener.SystemicGlobalEntityListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 实体监听器自动配置类
 *
 * @author Houtaroy
 */
@Configuration
public class EntityListenerAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "enableableGlobalEntityListener")
  public GlobalEntityListener enableableGlobalEntityListener() {
    return new EnableableGlobalEntityListener();
  }

  @Bean
  @ConditionalOnMissingBean(name = "systemicGlobalEntityListener")
  public GlobalEntityListener systemicGlobalEntityListener() {
    return new SystemicGlobalEntityListener();
  }

  @Bean
  @ConditionalOnMissingBean(name = "auditingGlobalEntityListener")
  public GlobalEntityListener auditingGlobalEntityListener(AuditorAware<?> auditorAware) {
    return new AuditingGlobalEntityListener<>(auditorAware);
  }

  @Bean
  @ConditionalOnMissingBean
  public EntityListenerFactory entityListenerFactory() {
    return new BeanEntityListenerFactory();
  }

  @Bean
  @ConditionalOnMissingBean
  public CrudServiceEntityListenerAspect crudServiceEntityListenerAspect(PlatformTransactionManager transactionManager,
                                                                         EntityListenerFactory entityListenerFactory) {

    return new CrudServiceEntityListenerAspect(transactionManager, entityListenerFactory);
  }
}
