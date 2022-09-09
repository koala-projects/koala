package cn.koala.database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * JDBC数据库表
 *
 * @author Houtaroy
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JdbcTable implements Table {
  protected String name;
  protected String remarks;
  protected List<JdbcColumn> columns;
}
