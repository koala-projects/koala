package cn.koala.jdbc;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JDBC表
 *
 * @author Houtaroy
 */
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Data
public class JdbcTable implements Table {
  protected String name;
  protected String remarks;

  /**
   * 从ResultSet生成
   *
   * @param resultSet ResultSet
   * @return JdbcTable
   * @throws SQLException SQL异常
   */
  public static JdbcTable fromResultSet(ResultSet resultSet) throws SQLException {
    return JdbcTable.builder()
      .name(resultSet.getString(JdbcNames.TABLE_NAME))
      .remarks(resultSet.getString(JdbcNames.REMARKS))
      .build();
  }
}
