package cn.koala.code.table;

import cn.koala.toolkit.jdbc.Column;
import cn.koala.toolkit.jdbc.DatabaseHelper;
import cn.koala.toolkit.jdbc.Table;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据库表上下文
 *
 * @author Houtaroy
 */
@Data
@SuperBuilder(toBuilder = true)
public class TableContext {
  private Table table;
  private List<Column> columns;
  private Column primary;

  public static TableContext from(Table table, DatabaseMetaData meta) throws SQLException {
    List<Column> columns = DatabaseHelper.getColumns(meta, null, null, table.getTableName(), null);
    return TableContext.builder()
      .table(table)
      .columns(columns)
      .primary(getPrimaryOrThrowException(columns))
      .build();
  }

  protected static Column getPrimaryOrThrowException(List<Column> columns) {
    return columns.stream()
      .filter(Column::getIsPrimaryKey)
      .findFirst()
      .orElseThrow(() -> new IllegalStateException("该数据库表没有主键, 无法生成上下文"));
  }
}
