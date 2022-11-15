package cn.koala.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC帮助类
 *
 * @author Houtaroy
 */
public abstract class JdbcHelper {
  /**
   * 获取数据库名称列表
   *
   * @param url      数据库连接
   * @param username 用户名
   * @param password 密码
   * @return 数据库名称列表
   * @throws SQLException SQL异常
   */
  public static List<String> getCatalogNames(String url, String username, String password) throws SQLException {
    return query(url, username, password, connection -> getCatalogNames(connection.getMetaData()));
  }

  /**
   * 获取表名称列表
   *
   * @param url        数据库连接
   * @param username   用户名
   * @param password   密码
   * @param schemaName 数据库名称
   * @return 表名称列表
   * @throws SQLException SQL异常
   */
  public static List<String> getTableNames(String url, String username, String password, String schemaName)
    throws SQLException {
    return query(url, username, password,
      connection -> getTableNames(connection.getMetaData(), schemaName, schemaName, null, null));
  }

  /**
   * 获取列列表
   *
   * @param url        数据库连接
   * @param username   用户名
   * @param password   密码
   * @param schemaName 数据库名称
   * @param tableName  表名
   * @return 列列表
   * @throws SQLException SQL异常
   */
  public static List<Column> getColumns(String url, String username, String password, String schemaName,
                                        String tableName) throws SQLException {
    return query(url, username, password,
      connection -> getColumns(connection.getMetaData(), schemaName, schemaName, tableName, null));
  }

  /**
   * 查询
   *
   * @param url      数据库连接
   * @param username 用户名
   * @param password 密码
   * @param query    查询
   * @param <T>      查询结果类型
   * @return 查询结果
   * @throws SQLException SQL异常
   */
  public static <T> T query(String url, String username, String password, JdbcQuery<T> query)
    throws SQLException {
    try (Connection connection = DriverManager.getConnection(url, username, password)) {
      return query.doQuery(connection);
    }
  }

  /**
   * 获取数据库名称列表
   *
   * @param metaData 数据库元数据
   * @return 数据库名称列表
   * @throws SQLException SQL异常
   */
  public static List<String> getCatalogNames(DatabaseMetaData metaData) throws SQLException {
    List<String> result = new ArrayList<>();
    ResultSet resultSet = metaData.getCatalogs();
    while (resultSet.next()) {
      result.add(resultSet.getString(JdbcNames.TABLE_CAT));
    }
    return result;
  }

  /**
   * 获取表名称列表
   *
   * @param metaData         数据库元数据
   * @param catalog          数据库名称
   * @param schemaPattern    数据库名称pattern
   * @param tableNamePattern 表名pattern
   * @param types            表类型数组
   * @return 数据库表名称列表
   * @throws SQLException SQL异常
   */
  public static List<String> getTableNames(DatabaseMetaData metaData, String catalog, String schemaPattern,
                                           String tableNamePattern, String[] types) throws SQLException {
    List<String> result = new ArrayList<>();
    ResultSet resultSet = metaData.getTables(catalog, schemaPattern, tableNamePattern, types);
    while (resultSet.next()) {
      result.add(resultSet.getString(JdbcNames.TABLE_NAME));
    }
    return result;
  }

  /**
   * 获取列列表
   *
   * @param metaData          数据库元数据
   * @param catalog           数据库名称
   * @param schemaPattern     数据库名称pattern
   * @param tableNamePattern  表名pattern
   * @param columnNamePattern 列名pattern
   * @return 列列表
   * @throws SQLException SQL异常
   */
  public static List<Column> getColumns(DatabaseMetaData metaData, String catalog, String schemaPattern,
                                        String tableNamePattern, String columnNamePattern) throws SQLException {
    List<Column> result = new ArrayList<>();
    List<String> primaryKeyNames = getPrimaryKeyNames(metaData, catalog, schemaPattern, tableNamePattern);
    ResultSet resultSet = metaData.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);
    while (resultSet.next()) {
      JdbcColumn column = JdbcColumn.fromResultSet(resultSet);
      column.setPrimaryKey(primaryKeyNames.contains(column.getName()));
      result.add(column);
    }
    return result;
  }

  /**
   * 获取主键名称列表
   *
   * @param metaData 数据库元数据
   * @param catalog  数据库名称
   * @param schema   数据库名称
   * @param table    表名
   * @return 主键名称列表
   * @throws SQLException SQL异常
   */
  protected static List<String> getPrimaryKeyNames(DatabaseMetaData metaData, String catalog, String schema,
                                                   String table)
    throws SQLException {
    List<String> result = new ArrayList<>();
    ResultSet resultSet = metaData.getPrimaryKeys(catalog, schema, table);
    while (resultSet.next()) {
      result.add(resultSet.getString(JdbcNames.COLUMN_NAME));
    }
    return result;
  }
}
