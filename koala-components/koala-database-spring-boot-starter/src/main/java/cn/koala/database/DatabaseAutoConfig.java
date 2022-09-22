package cn.koala.database;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据库自动配置
 *
 * @author Houtaroy
 */
@Configuration
public class DatabaseAutoConfig {

  /**
   * JDBC数据库服务的bean
   *
   * @return JDBC数据库服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public FilterableDatabaseService databaseService() {
    return new JdbcDatabaseService();
  }

  /**
   * 数据库接口的bean
   *
   * @param databaseService 数据库服务对象
   * @return 数据库接口对象
   */
  @Bean
  @ConditionalOnMissingBean
  public DatabaseApi databaseApi(FilterableDatabaseService databaseService) {
    return new DatabaseApiImpl(databaseService);
  }
}
