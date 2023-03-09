package cn.koala.database.autoconfigure;

import cn.koala.database.apis.TableApi;
import cn.koala.database.apis.TableApiImpl;
import cn.koala.database.services.DataSourceTableService;
import cn.koala.database.services.TableService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 数据库自动配置类
 *
 * @author Houtaroy
 */
@Configuration
public class DatabaseAutoConfiguration {
  @Bean
  @ConditionalOnMissingBean
  public TableService tableService(DataSource dataSource) {
    return new DataSourceTableService(dataSource);
  }

  @Bean
  @ConditionalOnMissingBean
  public TableApi tableApi(TableService tableService) {
    return new TableApiImpl(tableService);
  }
}
