package cn.koala.data;

import cn.koala.data.mybatis.DataSourceRepository;
import cn.koala.data.mybatis.DataSourceServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据源自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@MapperScan("cn.koala.data.mybatis")
public class DataSourceAutoConfig {
  /**
   * 数据源服务的bean
   *
   * @param dataSourceRepository 数据源存储库对象
   * @return 数据源服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public DataSourceService dataSourceService(DataSourceRepository dataSourceRepository) {
    return new DataSourceServiceImpl(dataSourceRepository);
  }

  /**
   * 数据源接口的bean
   *
   * @param dataSourceService 数据源服务对象
   * @return 数据源接口对象
   */
  @Bean
  @ConditionalOnMissingBean
  public DataSourceApi dataSourceApi(DataSourceService dataSourceService) {
    return new DataSourceApiImpl(dataSourceService);
  }
}
