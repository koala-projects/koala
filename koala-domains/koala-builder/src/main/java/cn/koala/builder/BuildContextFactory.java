package cn.koala.builder;

import cn.koala.toolkit.jdbc.Column;
import cn.koala.toolkit.jdbc.DatabaseHelper;
import cn.koala.toolkit.jdbc.Table;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 构建上下文工厂
 *
 * @author Houtaroy
 */
public class BuildContextFactory {
  private static final String SORT_COLUMN_NAME = "sort_index";
  private static final Set<String> STATE_COLUMN_NAMES = Set.of("is_enable", "is_system", "is_delete");
  private static final Set<String> AUDIT_COLUMN_NAMES = Set.of("create_user_id", "create_time", "last_update_user_id", "last_update_time", "delete_user_id", "delete_time");

  public static BuildContext create(DatabaseMetaData meta, Table table) throws SQLException {
    List<Column> columns = DatabaseHelper.getColumns(meta, null, null, table.getTableName(), null);
    Set<String> columnNames = columns.stream().map(Column::getColumnName).collect(Collectors.toSet());
    return BuildContext.builder()
      .table(table)
      .primary(getPrimaryOrThrowException(columns))
      .columns(columns)
      .sortModel(columnNames.contains(SORT_COLUMN_NAME))
      .stateModel(columnNames.containsAll(STATE_COLUMN_NAMES))
      .auditModel(columnNames.containsAll(AUDIT_COLUMN_NAMES))
      .build();
  }

  private static Column getPrimaryOrThrowException(List<Column> columns) {
    return columns.stream()
      .filter(Column::getIsPrimaryKey)
      .findFirst()
      .orElseThrow(() -> new IllegalStateException("该数据库表没有主键, 无法生成上下文"));
  }
}
