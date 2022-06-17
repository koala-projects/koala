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
  protected String name;
  protected String comment;
  protected List<JdbcColumn> columns;

  /**
   * 构造函数
   *
   * @param name 表名称
   */
  public JdbcTable(String name) {
    this.name = name;
  }
}
