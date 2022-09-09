package cn.koala.database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.JDBCType;

/**
 * JDBC数据库列实体
 *
 * @author Houtaroy
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JdbcColumn implements Column {
  protected String name;
  protected JDBCType type;
  protected Integer size;
  protected Integer decimalDigits;
  protected String remarks;
  protected boolean nullable;
  protected boolean autoIncrement;
  protected boolean primaryKey;

  public String getType() {
    return type.getName();
  }
}
