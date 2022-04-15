package cn.houtaroy.koala.component.druid;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Houtaroy
 */
@Data
@AllArgsConstructor
public class SelectColumn {
  protected String table;
  protected String name;
  protected String alias;

  public boolean isSelectAll() {
    return "*".equals(name);
  }
}
