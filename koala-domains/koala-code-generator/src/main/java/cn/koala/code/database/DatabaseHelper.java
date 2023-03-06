package cn.koala.code.database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库帮助类
 *
 * @author Houtaroy
 */
public abstract class DatabaseHelper {

  public static List<Table> getTables(DataSource dataSource, String catalog, String schema, String tableNamePattern) throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      return getTables(connection.getMetaData(), catalog, schema, tableNamePattern);
    }
  }

  public static List<Table> getTables(DatabaseMetaData meta, String catalog, String schema, String tableNamePattern) throws SQLException {
    List<Table> result = new ArrayList<>();
    ResultSet rs = meta.getTables(catalog, schema, tableNamePattern, new String[]{"TABLE"});
    while (rs.next()) {
      result.add(Table.builder()
        .tableName(rs.getString("TABLE_NAME"))
        .remarks(rs.getString("REMARKS"))
        .build());
    }
    return result;
  }

  public static Table getTable(DataSource dataSource, String catalog, String schema, String tableName, boolean includedColumns) throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      return getTable(connection.getMetaData(), catalog, schema, tableName, includedColumns);
    }
  }

  public static Table getTable(DatabaseMetaData meta, String catalog, String schema, String tableName, boolean includedColumns) throws SQLException {
    ResultSet rs = meta.getTables(catalog, schema, tableName, new String[]{"TABLE"});
    if (!rs.next()) {
      throw new SQLException("表[%s]不存在".formatted(tableName));
    }
    Table result = Table.builder()
      .tableName(rs.getString("TABLE_NAME"))
      .remarks(rs.getString("REMARKS"))
      .build();
    if (includedColumns) {
      List<Column> columns = getColumns(meta, catalog, schema, tableName, null);
      result.setColumns(columns);
    }
    return result;
  }

  public static List<Column> getColumns(DatabaseMetaData meta, String catalog, String schema, String tableName, String columnNamePattern) throws SQLException {
    List<Column> result = new ArrayList<>();
    ResultSet rs = meta.getColumns(catalog, schema, tableName, columnNamePattern);
    List<String> primaryColumnNames = getPrimaryColumnNames(meta, catalog, schema, tableName);
    while (rs.next()) {
      String columnName = rs.getString("COLUMN_NAME");
      result.add(Column.builder()
        .columnName(columnName)
        .dataType(rs.getInt("DATA_TYPE"))
        .columnSize(rs.getInt("COLUMN_SIZE"))
        .decimalDigits(rs.getInt("DECIMAL_DIGITS"))
        .remarks(rs.getString("REMARKS"))
        .isNullable("YES".equals(rs.getString("IS_NULLABLE")))
        .isAutoincrement("YES".equals(rs.getString("IS_AUTOINCREMENT")))
        .isPrimaryKey(primaryColumnNames.contains(columnName))
        .build());
    }
    return result;
  }

  public static List<String> getPrimaryColumnNames(DatabaseMetaData meta, String catalog, String schema, String table) throws SQLException {
    List<String> result = new ArrayList<>();
    ResultSet rs = meta.getPrimaryKeys(catalog, schema, table);
    while (rs.next()) {
      result.add(rs.getString("COLUMN_NAME"));
    }
    return result;
  }
}
