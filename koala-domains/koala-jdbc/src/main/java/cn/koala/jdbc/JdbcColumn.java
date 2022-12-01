package cn.koala.jdbc;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JDBC列
 *
 * @author Houtaroy
 */
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Data
public class JdbcColumn implements Column {
  protected String name;
  protected String type;
  protected int dataType;
  protected Integer size;
  protected Integer decimalDigits;
  protected String remarks;
  protected boolean nullable;
  protected boolean autoIncrement;
  protected boolean primaryKey;

  /**
   * 从ResultSet生成
   *
   * @param resultSet ResultSet
   * @return JdbcColumn
   * @throws SQLException SQL异常
   */
  public static JdbcColumn fromResultSet(ResultSet resultSet) throws SQLException {
    return JdbcColumn.builder()
      .name(resultSet.getString(JdbcNames.COLUMN_NAME))
      .type(resultSet.getString(JdbcNames.TYPE_NAME))
      .dataType(resultSet.getInt(JdbcNames.DATA_TYPE))
      .size(resultSet.getInt(JdbcNames.COLUMN_SIZE))
      .decimalDigits(resultSet.getInt(JdbcNames.DECIMAL_DIGITS))
      .remarks(resultSet.getString(JdbcNames.REMARKS))
      .nullable(resultSet.getBoolean(JdbcNames.IS_NULLABLE))
      .autoIncrement(resultSet.getBoolean(JdbcNames.IS_AUTOINCREMENT))
      .build();
  }
}
