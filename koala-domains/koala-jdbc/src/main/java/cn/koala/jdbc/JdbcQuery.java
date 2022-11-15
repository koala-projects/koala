package cn.koala.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * JDBC查询
 *
 * @param <R> 查询结果类型
 * @author Houtaroy
 */
@FunctionalInterface
public interface JdbcQuery<R> {
  /**
   * 执行查询
   *
   * @param connection 数据库连接
   * @return 查询结果
   * @throws SQLException SQL异常
   */
  R doQuery(Connection connection) throws SQLException;
}
