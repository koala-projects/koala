package cn.koala.datasource.support;

import cn.koala.datasource.support.mapper.DruidMapper;
import com.alibaba.druid.spring.boot3.autoconfigure.DruidDataSourceWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;

/**
 * Druid连接池动态数据源工厂
 *
 * @author Houtaroy
 */
@Slf4j
@RequiredArgsConstructor
public class DruidDynamicDataSourceFactory extends AbstractDynamicDataSourceFactory<DruidDataSourceWrapper> {

  private final Environment environment;

  @Override
  public DruidDataSourceWrapper create(DataSourceProperties properties) {

    DruidDataSourceWrapper dataSource = createDataSource(properties, DruidDataSourceWrapper.class);

    Binder binder = Binder.get(environment);
    binder.bind("spring.datasource.druid", DruidDataSourceWrapper.class)
      .ifBound(config -> {
        try {
          DruidMapper.INSTANCE.copy(config, dataSource);
        } catch (Exception e) {
          LOGGER.error("[koala-data-source]: 数据源[name = {}]配置拷贝失败", properties.getName(), e);
        }
      });

    return dataSource;
  }
}
