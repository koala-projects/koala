package cn.koala.datasource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import javax.sql.DataSource;

/**
 * 动态数据源工厂
 *
 * @author Houtaroy
 */
public interface DynamicDataSourceFactory<T extends DataSource> {

  T create(DataSourceProperties properties);
}
