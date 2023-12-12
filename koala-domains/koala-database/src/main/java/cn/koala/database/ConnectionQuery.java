package cn.koala.database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库连接查询
 *
 * @param <R> 返回值类型
 * @author Houtaroy
 */
@Deprecated
@FunctionalInterface
public interface ConnectionQuery<R> {
  R query(Connection connection) throws SQLException;
}
