package cn.koala.utils;

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
public final class JdbcUtil {
  private JdbcUtil() {
  }

  /**
   * 连接数据库
   *
   * @param properties 连接属性
   * @return 数据库连接
   * @throws SQLException SQL异常
   */
  public static Connection connect(ConnectionProperties properties) throws SQLException {
    return DriverManager.getConnection(properties.getUrl(), properties.getUsername(), properties.getPassword());
  }

  /**
   * 获取数据库指定表的所有列
   *
   * @param properties 连接属性
   * @param tableName  表名
   * @return 列列表
   * @throws SQLException SQL异常
   */
  public static List<JdbcColumn> columns(ConnectionProperties properties, String tableName) throws SQLException {
    Connection connection = connect(properties);
    List<JdbcColumn> result = columns(connection.getMetaData(), properties.getCatalog(), tableName);
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
  public static List<JdbcColumn> columns(DatabaseMetaData metaData, String catalog, String tableName)
    throws SQLException {
    List<JdbcColumn> result = new ArrayList<>();
    ResultSet rs = metaData.getColumns(catalog, null, tableName, null);
    while (rs.next()) {
      result.add(new JdbcColumn(rs));
    }
    return result;
  }

  /**
   * 获取数据库表
   *
   * @param properties 连接属性
   * @param tableCode  表编码
   * @return 表
   * @throws SQLException SQL异常
   */
  public static JdbcTable table(ConnectionProperties properties, String tableCode) throws SQLException {
    for (JdbcTable table : tables(properties)) {
      if (tableCode.equals(table.getCode())) {
        return table;
      }
    }
    return new JdbcTable(tableCode);
  }

  /**
   * 根据连接属性获取表名称列表
   *
   * @param properties 连接属性
   * @return 表名称列表
   * @throws SQLException SQL异常
   */
  public static List<JdbcTable> tables(ConnectionProperties properties) throws SQLException {
    List<JdbcTable> result = new ArrayList<>();
    Connection connection = connect(properties);
    DatabaseMetaData metaData = connection.getMetaData();
    ResultSet rs = metaData.getTables(properties.getCatalog(), null, null, null);
    while (rs.next()) {
      result.add(
        new JdbcTable(
          rs.getString(JdbcLabels.TABLE_NAME),
          rs.getString(JdbcLabels.COMMENT),
          columns(metaData, properties.getCatalog(), rs.getString(JdbcLabels.TABLE_NAME))
        )
      );
    }
    connection.close();
    return result;
  }
}
