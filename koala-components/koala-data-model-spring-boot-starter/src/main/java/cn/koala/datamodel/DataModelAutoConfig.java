package cn.koala.datamodel;

import cn.koala.datamodel.mybatis.DataRepository;
import cn.koala.datamodel.mybatis.MetadataRepository;
import cn.koala.datamodel.mybatis.MyBatisDataService;
import cn.koala.datamodel.mybatis.MyBatisMetadataService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据模型自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@MapperScan(basePackages = "cn.koala.datamodel.mybatis")
public class DataModelAutoConfig {

  /**
   * 元数据服务的bean
   *
   * @param metadataRepository 元数据存储库对象
   * @return 元数据服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public MetadataService metadataService(MetadataRepository metadataRepository) {
    return new MyBatisMetadataService(metadataRepository);
  }

  /**
   * 数据服务的bean
   *
   * @param dataRepository 数据存储库对象
   * @return 数据服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public DataService dataService(DataRepository dataRepository) {
    return new MyBatisDataService(dataRepository);
  }

  /**
   * 元数据接口的bean
   *
   * @param metadataService 元数据服务对象
   * @return 元数据接口对象
   */
  @Bean
  @ConditionalOnMissingBean
  public MetadataApi metadataApi(MetadataService metadataService) {
    return new MetadataApiImpl(metadataService);
  }

  /**
   * 数据接口的bean
   *
   * @param dataService 数据服务对象
   * @return 数据接口对象
   */
  @Bean
  @ConditionalOnMissingBean
  public DataApi dataApi(DataService dataService) {
    return new DataApiImpl(dataService);
  }
}
