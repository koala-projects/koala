package cn.koala.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源实现类
 *
 * @author Houtaroy
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

  public static final ThreadLocal<Object> CONTEXT_HOLDER = new ThreadLocal<>();

  @Override
  protected Object determineCurrentLookupKey() {
    return CONTEXT_HOLDER.get();
  }
}
