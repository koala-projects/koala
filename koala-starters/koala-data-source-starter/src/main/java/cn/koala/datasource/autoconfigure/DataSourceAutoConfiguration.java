package cn.koala.datasource.autoconfigure;

import cn.koala.datasource.DynamicDataSource;
import cn.koala.datasource.DynamicDataSourceAspect;
import cn.koala.datasource.DynamicDataSourceFactory;
import cn.koala.datasource.support.HikariDynamicDataSourceFactory;
import cn.koala.datasource.support.TomcatDynamicDataSourceFactory;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源自动配置类
 *
 * @author Houtaroy
 */
@EnableConfigurationProperties(DynamicDataSourceProperties.class)
public class DataSourceAutoConfiguration {

  @Bean
  @ConditionalOnClass(HikariDataSource.class)
  @ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.zaxxer.hikari.HikariDataSource", matchIfMissing = true)
  public HikariDynamicDataSourceFactory hikariDynamicDataSourceFactory(Environment environment) {
    return new HikariDynamicDataSourceFactory(environment);
  }

  @Bean
  @ConditionalOnClass(org.apache.tomcat.jdbc.pool.DataSource.class)
  @ConditionalOnProperty(name = "spring.datasource.type", havingValue = "org.apache.tomcat.jdbc.pool.DataSource")
  public TomcatDynamicDataSourceFactory tomcatDynamicDataSourceFactory(Environment environment) {
    return new TomcatDynamicDataSourceFactory(environment);
  }

  @Bean
  public DataSource dataSource(DynamicDataSourceProperties properties, DynamicDataSourceFactory<?> factory) {

    Assert.notEmpty(properties.getDynamic(), "请至少填写一个数据源配置");


    Map<Object, Object> targetDataSources = new HashMap<>();
    DataSource defaultDataSource = null;
    for (DataSourceProperties dataSourceProperties : properties.getDynamic()) {
      DataSource dataSource = factory.create(dataSourceProperties);
      targetDataSources.put(dataSourceProperties.getName(), dataSource);
      if (defaultDataSource == null) {
        defaultDataSource = dataSource;
      }
    }

    Assert.notNull(defaultDataSource, "默认数据源创建失败, 请检查配置");

    DynamicDataSource routingDataSource = new DynamicDataSource();
    routingDataSource.setTargetDataSources(targetDataSources);
    routingDataSource.setDefaultTargetDataSource(defaultDataSource);
    return routingDataSource;
  }
  
  @Bean
  public DynamicDataSourceAspect dynamicDataSourceAspect() {
    return new DynamicDataSourceAspect();
  }
}
