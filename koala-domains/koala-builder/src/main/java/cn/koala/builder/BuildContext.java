package cn.koala.builder;

import cn.koala.toolkit.jdbc.Column;
import cn.koala.toolkit.jdbc.Table;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 构建上下文
 *
 * @author Houtaroy
 */
@Data
@SuperBuilder(toBuilder = true)
public class BuildContext {
  protected Table table;
  protected Column primary;
  protected List<Column> columns;
  protected boolean sortModel;
  protected boolean stateModel;
  protected boolean auditModel;
}
