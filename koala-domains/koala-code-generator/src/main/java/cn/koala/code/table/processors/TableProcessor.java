package cn.koala.code.table.processors;

import cn.koala.code.BaseContextProcessor;
import cn.koala.code.table.TableContext;

import java.util.Map;

/**
 * 数据库表上下文加工器
 *
 * @author Houtaroy
 */
public class TableProcessor extends BaseContextProcessor<TableContext> {
  public TableProcessor() {
    super(TableContext.class);
  }

  @Override
  protected Map<String, Object> doProcess(TableContext context) {
    return Map.of(
      "table", context.getTable(),
      "columns", context.getColumns(),
      "primary", context.getPrimary()
    );
  }
}
