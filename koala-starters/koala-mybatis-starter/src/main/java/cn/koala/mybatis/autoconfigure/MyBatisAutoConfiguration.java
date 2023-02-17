package cn.koala.mybatis.autoconfigure;

import cn.koala.mybatis.AuditorIdSupplier;
import cn.koala.mybatis.CrudHelper;
import cn.koala.mybatis.EnhancedEnumTypeHandler;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@AutoConfigureAfter(MybatisAutoConfiguration.class)
@EnableTransactionManagement
public class MyBatisAutoConfiguration {
  /**
   * MyBatis中文枚举TypeHandler自定义配置
   *
   * @return 自定义配置
   */
  @Bean
  public ConfigurationCustomizer enhancedEnumTypeConfigurationCustomizer(
    ObjectProvider<AuditorIdSupplier<?>> auditorIdSupplier) {
    auditorIdSupplierCustomizer(auditorIdSupplier.getIfAvailable());
    return configuration -> configuration.getTypeHandlerRegistry().register(EnhancedEnumTypeHandler.class);
  }

  /**
   * 审计员ID提供者定制器
   *
   * @param auditorIdSupplier 审计员ID提供者
   */
  private void auditorIdSupplierCustomizer(AuditorIdSupplier<?> auditorIdSupplier) {
    CrudHelper.setAuditorIdSupplier(auditorIdSupplier);
  }
}
