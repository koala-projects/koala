package cn.koala.eucalyptus;

import cn.koala.utils.JdbcColumn;
import lombok.Data;

/**
 * @author Houtaroy
 */
@Data
public class JdbcDomainProperty implements DomainProperty {
  protected String code;
  protected String name;
  protected String type;
  protected JdbcColumn column;
  protected boolean id;

  /**
   * 构造函数
   *
   * @param column 列对象
   */
  public JdbcDomainProperty(JdbcColumn column) {
    this.code = column.getName();
    this.name = column.getComment();
    this.type = column.getType();
    this.column = column;
    this.id = "id".equalsIgnoreCase(column.getName());
  }
}
