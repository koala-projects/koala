package cn.koala.datasource.support;

import cn.koala.datasource.support.mapper.TomcatMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.core.env.Environment;

import java.sql.SQLException;

/**
 * Tomcat连接池动态数据源工厂
 *
 * @author Houtaroy
 */
@Slf4j
@RequiredArgsConstructor
public class TomcatDynamicDataSourceFactory extends AbstractDynamicDataSourceFactory<DataSource> {

  private final Environment environment;
  private final TomcatMapper mapper = Mappers.getMapper(TomcatMapper.class);

  @Override
  public DataSource create(DataSourceProperties properties) {

    DataSource dataSource = createDataSource(
      properties,
      DataSource.class
    );

    Binder binder = Binder.get(environment);
    binder.bind("spring.datasource.tomcat", DataSource.class)
      .ifBound(config -> {
        try {
          mapper.copy(config.getPoolProperties(), dataSource.getPoolProperties());
        } catch (SQLException e) {
          LOGGER.error("[koala-data-source]: 数据源[name = {}]配置拷贝失败", properties.getName(), e);
        }
      });

    DatabaseDriver databaseDriver = DatabaseDriver.fromJdbcUrl(properties.determineUrl());
    String validationQuery = databaseDriver.getValidationQuery();
    if (validationQuery != null) {
      dataSource.setTestOnBorrow(true);
      dataSource.setValidationQuery(validationQuery);
    }

    return dataSource;
  }
}
