package cn.koala.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Houtaroy
 */
@SuppressWarnings("PMD")
public class JdbcDatabaseService implements DatabaseService {
  /**
   * 根据连接属性获取数据库表
   *
   * @param properties 连接属性
   * @return 数据库表列表
   * @throws SQLException SQL异常
   */
  @Override
  public List<Table> tables(ConnectionProperties properties) throws SQLException {
    List<Table> result = new ArrayList<>();
    Connection connection = connect(properties);
    DatabaseMetaData metaData = connection.getMetaData();
    ResultSet rs = metaData.getTables(properties.getCatalog(), null, null, null);
    while (rs.next()) {
      String tableName = rs.getString("TABLE_NAME");
      result.add(
        Table.builder().name(tableName).comment(rs.getString("REMARKS"))
          .columns(columns(metaData, properties.getCatalog(), tableName)).build()
      );
    }
    connection.close();
    return result;
  }

  /**
   * 获取数据库指定表的所有列
   *
   * @param properties 连接属性
   * @param tableName  表名
   * @return 列列表
   * @throws SQLException SQL异常
   */
  protected List<Column> columns(ConnectionProperties properties, String tableName) throws SQLException {
    Connection connection = connect(properties);
    List<Column> result = columns(connection.getMetaData(), properties.getCatalog(), tableName);
    connection.close();
    return result;
  }

  /**
   * 获取数据库指定表的所有列
   *
   * @param metaData  数据库元数据
   * @param catalog   数据库名称
   * @param tableName 表名
   * @return 列列表
   * @throws SQLException SQL异常
   */
  protected List<Column> columns(DatabaseMetaData metaData, String catalog, String tableName) throws SQLException {
    List<Column> result = new ArrayList<>();
    ResultSet rs = metaData.getColumns(catalog, null, tableName, null);
    while (rs.next()) {
      result.add(new Column(rs));
    }
    return result;
  }

  /**
   * 连接数据库
   *
   * @param properties 连接属性
   * @return 数据库连接
   * @throws SQLException SQL异常
   */
  protected Connection connect(ConnectionProperties properties) throws SQLException {
    return DriverManager.getConnection(properties.getUrl(), properties.getUsername(), properties.getPassword());
  }
}
