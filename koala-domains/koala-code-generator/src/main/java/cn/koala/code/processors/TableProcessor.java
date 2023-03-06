package cn.koala.code.processors;

import cn.koala.code.database.Table;
import org.springframework.lang.NonNull;

import java.util.Map;

/**
 * 数据库表上下文加工器
 *
 * @author Houtaroy
 */
public class TableProcessor extends BaseContextProcessor<Table> {
  public TableProcessor() {
    super(Table.class);
  }

  @Override
  protected Map<String, Object> doProcess(@NonNull Table context) {
    return Map.of(
      "table", context,
      "columns", context.getColumns()
    );
  }
}
