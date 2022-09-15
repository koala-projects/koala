package cn.koala.database;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC服务, 基于JDBC实现
 *
 * @author Houtaroy
 */
@Slf4j
public class JdbcDatabaseService implements FilterableDatabaseService, DatabaseService {

  @Override
  public List<Table> getTables(ConnectProperties connectProperties) {
    try {
      Connection connection = getConnection(connectProperties);
      List<Table> result = getTables(connection.getMetaData(), connectProperties.getCatalog());
      connection.close();
      return result;
    } catch (SQLException e) {
      LOGGER.error("JDBC查询失败", e);
      return new ArrayList<>();
    }
  }

  /**
   * 获取数据库连接
   *
   * @param connectProperties 连接属性
   * @return 数据库连接
   * @throws SQLException SQLException
   */
  protected Connection getConnection(ConnectProperties connectProperties) throws SQLException {
    return DriverManager.getConnection(
      connectProperties.getUrl(), connectProperties.getUser(), connectProperties.getPassword());
  }

  /**
   * 获取数据库的全部表
   *
   * @param databaseMetaData 数据库元数据
   * @param catalog          数据库名
   * @return 全部表
   * @throws SQLException SQLException
   */
  protected List<Table> getTables(DatabaseMetaData databaseMetaData, String catalog) throws SQLException {
    ResultSet rs = databaseMetaData.getTables(catalog, null, null, null);
    List<Table> result = new ArrayList<>();
    while (rs.next()) {
      String tableName = rs.getString(JdbcLabels.TABLE_NAME);
      result.add(new JdbcTable(
        tableName,
        rs.getString(JdbcLabels.REMARKS),
        getColumns(databaseMetaData, catalog, tableName))
      );
    }
    return result;
  }

  /**
   * 获取数据库表的全部列
   *
   * @param databaseMetaData 数据库元数据
   * @param catalog          数据库名称
   * @param table            表名
   * @return 全部列
   * @throws SQLException SQLException
   */
  protected List<JdbcColumn> getColumns(DatabaseMetaData databaseMetaData, String catalog, String table)
    throws SQLException {
    List<JdbcColumn> result = new ArrayList<>();
    String primaryKeyColumnName = getPrimaryKeyColumnName(databaseMetaData, catalog, table);
    ResultSet rs = databaseMetaData.getColumns(catalog, null, table, null);
    while (rs.next()) {
      String columnName = rs.getString(JdbcLabels.COLUMN_NAME);
      result.add(new JdbcColumn(
        columnName,
        JDBCType.valueOf(rs.getInt(JdbcLabels.COLUMN_TYPE)),
        rs.getInt(JdbcLabels.COLUMN_SIZE),
        rs.getInt(JdbcLabels.DECIMAL_DIGITS),
        rs.getString(JdbcLabels.REMARKS),
        rs.getBoolean(JdbcLabels.IS_NULLABLE),
        rs.getBoolean(JdbcLabels.IS_AUTOINCREMENT),
        primaryKeyColumnName.equals(columnName)
      ));
    }
    return result;
  }

  /**
   * 获取数据库表的主键列名称
   *
   * @param databaseMetaData 数据库元数据
   * @param catalog          数据库名称
   * @param table            表名
   * @return 主键列名称
   * @throws SQLException SQLException
   */
  protected String getPrimaryKeyColumnName(DatabaseMetaData databaseMetaData, String catalog, String table)
    throws SQLException {
    String result = "";
    ResultSet rs = databaseMetaData.getPrimaryKeys(catalog, null, table);
    while (rs.next()) {
      result = rs.getString(JdbcLabels.COLUMN_NAME);
    }
    return result;
  }
}
