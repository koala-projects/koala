package cn.koala.mybatis;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Houtaroy
 */
@Configuration
@AutoConfigureAfter(MybatisAutoConfiguration.class)
@EnableConfigurationProperties(MyBatisProperties.class)
@EnableTransactionManagement
@RequiredArgsConstructor
public class MyBatisAutoConfig {
  /**
   * MyBatis中文枚举TypeHandler自定义配置
   *
   * @return 自定义配置
   */
  @Bean
  public ConfigurationCustomizer valueNameEnumTypeHandlerConfigurationCustomizer() {
    return configuration -> configuration.getTypeHandlerRegistry().register(ValueNameEnumTypeHandler.class);
  }

  /**
   * MyBatis TypeHandler自定义配置
   *
   * @param properties MyBatis配置
   * @return 自定义配置
   */
  @Bean
  public ConfigurationCustomizer typeHandlerConfigurationCustomizer(MyBatisProperties properties) {
    return configuration -> {
      TypeHandlerRegistry registry = configuration.getTypeHandlerRegistry();
      properties.getTypeHandlerPackages().forEach(registry::register);
    };
  }
}
