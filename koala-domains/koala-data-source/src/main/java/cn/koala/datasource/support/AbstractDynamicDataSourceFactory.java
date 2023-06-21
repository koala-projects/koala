package cn.koala.datasource.support;

import cn.koala.datasource.DynamicDataSourceFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import javax.sql.DataSource;

/**
 * 动态数据源工厂抽象类
 *
 * @author Houtaroy
 */
public abstract class AbstractDynamicDataSourceFactory<T extends DataSource> implements DynamicDataSourceFactory<T> {

  protected T createDataSource(DataSourceProperties properties, Class<T> type) {
    return properties.initializeDataSourceBuilder().type(type).build();
  }
}
