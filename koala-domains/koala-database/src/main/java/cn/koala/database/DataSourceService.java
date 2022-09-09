package cn.koala.database;

import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * DataSource服务, 继承自{@link JdbcService}, 重写获取连接方式
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class DataSourceService extends JdbcService {

  protected final DataSource dataSource;

  @Override
  protected Connection getConnection(ConnectProperties connectProperties) throws SQLException {
    return dataSource.getConnection();
  }
}
