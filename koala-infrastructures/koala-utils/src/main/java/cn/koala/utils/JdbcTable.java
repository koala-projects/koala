package cn.koala.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Houtaroy
 */
@AllArgsConstructor
@Data
public class JdbcTable {
  protected String code;
  protected String name;
  protected List<JdbcColumn> columns;

  public JdbcTable(String code) {
    this.code = code;
  }
}
