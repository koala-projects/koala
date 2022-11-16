package cn.koala.data;

import cn.koala.jdbc.Column;
import cn.koala.jdbc.JdbcHelper;
import cn.koala.jdbc.Table;
import cn.koala.persistence.CrudService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据源服务
 *
 * @author Houtaroy
 */
public interface DataSourceService extends CrudService<String, DataSource> {
  /**
   * 是否可连接的
   *
   * @param dataSource 数据源
   * @return 是否可连接的
   */
  default boolean isConnectable(DataSource dataSource) {
    try (Connection ignored =
           JdbcHelper.getConnection(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword())) {
      return true;
    } catch (SQLException e) {
      return false;
    }
  }

  /**
   * 获取数据库名称列表
   *
   * @param dataSource 数据源
   * @return 数据库名称列表
   */
  default List<String> getCatalogNames(DataSource dataSource) {
    try {
      return JdbcHelper.getCatalogNames(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
    } catch (SQLException e) {
      throw new IllegalStateException("数据源异常, 请联系管理员");
    }
  }

  /**
   * 获取表列表
   *
   * @param dataSource 数据源
   * @param schemaName 数据库名称
   * @return 表列表
   */
  default List<Table> getTables(DataSource dataSource, String schemaName) {
    try {
      return JdbcHelper.getTables(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword(), schemaName);
    } catch (SQLException e) {
      throw new IllegalStateException("数据源异常, 请联系管理员");
    }
  }

  /**
   * 获取列列表
   *
   * @param dataSource 数据源
   * @param schemaName 数据库名称
   * @param tableName  表名
   * @return 列列表
   */
  default List<Column> getColumns(DataSource dataSource, String schemaName, String tableName) {
    try {
      return JdbcHelper.getColumns(
        dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword(), schemaName, tableName
      );
    } catch (SQLException e) {
      throw new IllegalStateException("数据源异常, 请联系管理员");
    }
  }
}
