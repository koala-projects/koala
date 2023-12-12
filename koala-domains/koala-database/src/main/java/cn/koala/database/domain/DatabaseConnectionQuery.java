package cn.koala.database.domain;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库连接查询
 *
 * @param <T> 查询结果类型
 * @author Houtaroy
 */
@FunctionalInterface
public interface DatabaseConnectionQuery<T> {

  T query(Connection connection) throws SQLException;
}
