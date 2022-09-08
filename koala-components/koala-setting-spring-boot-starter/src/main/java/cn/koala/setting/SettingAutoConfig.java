package cn.koala.setting;

import cn.koala.datamodel.DataService;
import cn.koala.datamodel.MetadataService;
import cn.koala.datamodel.mybatis.DataRepository;
import cn.koala.datamodel.mybatis.MetadataRepository;
import cn.koala.datamodel.mybatis.MyBatisDataService;
import cn.koala.datamodel.mybatis.MyBatisMetadataService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 设置自动装配设置
 *
 * @author Houtaroy
 */
@Configuration
@MapperScan(basePackages = "cn.koala.datamodel.mybatis")
public class SettingAutoConfig {

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
   * 设置定义服务的bean
   *
   * @param metadataService 元数据服务
   * @return 设置定义服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public SettingDefinitionService settingDefinitionService(MetadataService metadataService) {
    return new DefaultSettingDefinitionService(metadataService);
  }

  /**
   * 设置服务的bean
   *
   * @param dataService     数据服务
   * @param metadataService 元数据服务
   * @return 设置服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public SettingService settingService(DataService dataService, MetadataService metadataService) {
    return new DefaultSettingService(dataService, metadataService);
  }

  /**
   * 设置定义接口的bean
   *
   * @param settingDefinitionService 设置定义服务
   * @return 设置定义接口对象
   */
  @Bean
  @ConditionalOnMissingBean
  public SettingDefinitionApi settingDefinitionApi(SettingDefinitionService settingDefinitionService) {
    return new SettingDefinitionApiImpl(settingDefinitionService);
  }

  /**
   * 设置接口的bean
   *
   * @param settingService 设置服务
   * @return 设置接口对象
   */
  @Bean
  @ConditionalOnMissingBean
  public SettingApi settingApi(SettingService settingService) {
    return new SettingApiImpl(settingService);
  }

  /**
   * 设置注册器的bean
   *
   * @param settingService 设置服务
   * @return 设置注册器对象
   */
  @Bean
  @ConditionalOnMissingBean
  public SettingRegistry settingRegistry(SettingService settingService) {
    return new SimpleSettingRegistry(settingService);
  }

  /**
   * 自动设置注册器的bean
   *
   * @param settingDefinitionService 设置定义服务
   * @param settingRegistry          设置注册器
   * @param settingRegistrations     设置注册记录
   * @return 自动设置注册器对象
   */
  @Bean
  @ConditionalOnMissingBean
  public AutomaticSettingRegistry automaticSettingRegistry(SettingDefinitionService settingDefinitionService,
                                                           SettingRegistry settingRegistry,
                                                           List<SettingRegistration> settingRegistrations) {
    return new AutomaticSettingRegistry(settingDefinitionService, settingRegistry, settingRegistrations);
  }
}
