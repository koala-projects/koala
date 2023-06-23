package cn.koala.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源实现类
 *
 * @author Houtaroy
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

  public static final ThreadLocal<String> KEY_HOLDER = new ThreadLocal<>();

  @Override
  protected String determineCurrentLookupKey() {
    return KEY_HOLDER.get();
  }
}
