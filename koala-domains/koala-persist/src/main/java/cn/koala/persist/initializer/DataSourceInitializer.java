package cn.koala.persist.initializer;

import javax.sql.DataSource;

/**
 * 数据源初始化器
 *
 * @author Houtaroy
 */
public interface DataSourceInitializer {

  String getName();

  void init(DataSource dataSource) throws Exception;
}
