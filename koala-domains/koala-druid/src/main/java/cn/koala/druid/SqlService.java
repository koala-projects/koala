package cn.koala.druid;

import com.alibaba.druid.DbType;

import java.util.Map;

/**
 * @author Houtaroy
 */
public interface SqlService {

  /**
   * 编译sql
   *
   * @param sql        sql
   * @param parameters 参数
   * @return 编译后的sql
   */
  default String compile(String sql, Map<String, Object> parameters) {
    String result = sql;
    for (String key : parameters.keySet()) {
      result = result.replaceAll(String.format("#\\{%s}", key), parameters.get(key).toString());
    }
    return result;
  }

  /**
   * 是否有SQL注入
   *
   * @param dbType 数据库类型
   * @param sql    sql
   * @return 是否有SQL注入
   * @throws NoSuchWallProviderException 没有找到SQL防火墙异常
   */
  boolean isInjection(DbType dbType, String sql) throws NoSuchWallProviderException;
}
