package cn.koala.datamodel;

import cn.koala.datamodel.mybatis.DataElementRepository;
import cn.koala.datamodel.mybatis.DataElementServiceImpl;
import cn.koala.datamodel.mybatis.DataRecordRepository;
import cn.koala.datamodel.mybatis.DataRecordServiceImpl;
import cn.koala.datamodel.mybatis.DataServiceImpl;
import cn.koala.datamodel.mybatis.MetadataRepository;
import cn.koala.datamodel.mybatis.MetadataServiceImpl;
import cn.koala.datamodel.mybatis.PropertyRepository;
import cn.koala.datamodel.mybatis.PropertyServiceImpl;
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
   * 属性服务的bean
   *
   * @param propertyRepository 属性存储库对象
   * @return 属性服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public PropertyService propertyService(PropertyRepository propertyRepository) {
    return new PropertyServiceImpl(propertyRepository);
  }

  /**
   * 元数据服务的bean
   *
   * @param metadataRepository 元数据存储库对象
   * @param propertyService    属性服务对象
   * @param dataRecordService  数据记录服务对象
   * @return 元数据服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public MetadataService metadataService(MetadataRepository metadataRepository, PropertyService propertyService,
                                         DataRecordService dataRecordService) {
    return new MetadataServiceImpl(metadataRepository, propertyService, dataRecordService);
  }

  /**
   * 数据元服务的bean
   *
   * @param repository 数据元存储库
   * @return 数据元服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public DataElementService dataElementService(DataElementRepository repository) {
    return new DataElementServiceImpl(repository);
  }

  /**
   * 数据记录服务的bean
   *
   * @param repository 数据记录存储库
   * @return 数据记录服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public DataRecordService dataRecordService(DataRecordRepository repository) {
    return new DataRecordServiceImpl(repository);
  }

  /**
   * 数据服务的bean
   *
   * @param dataRecordService  数据记录服务对象
   * @param dataElementService 数据元服务对象
   * @param metadataService    元数据服务对象
   * @return 数据服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public DataService dataService(DataRecordService dataRecordService, DataElementService dataElementService,
                                 MetadataService metadataService) {
    return new DataServiceImpl(dataRecordService, dataElementService, metadataService);
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
