package cn.houtaroy.koala.component.druid;

import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import lombok.Data;

/**
 * @author Houtaroy
 */
@Data
public class SelectTable {
  protected String name;
  protected String alias;

  /**
   * 构造函数
   *
   * @param expr SQL表表达式
   */
  public SelectTable(SQLExprTableSource expr) {
    this.name = expr.getTableName();
    this.alias = expr.getAlias();
  }
}
