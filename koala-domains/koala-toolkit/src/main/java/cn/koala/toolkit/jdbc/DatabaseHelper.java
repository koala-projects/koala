package cn.koala.toolkit.jdbc;

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

  public static List<Table> getTables(DatabaseMetaData meta, String catalog, String schema, String tableNamePattern) throws SQLException {
    List<Table> result = new ArrayList<>();
    ResultSet rs = meta.getTables(catalog, schema, tableNamePattern, new String[]{"TABLE"});
    while (rs.next()) {
      result.add(new Table(rs.getString("TABLE_NAME"), rs.getString("REMARKS")));
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
