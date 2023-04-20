package cn.koala.code.processors;

import cn.koala.database.DatabaseTable;
import org.springframework.lang.NonNull;

import java.util.Map;

/**
 * 数据库表上下文加工器
 *
 * @author Houtaroy
 */
public class TableProcessor extends AbstractContextProcessor<DatabaseTable> {
  public TableProcessor() {
    super(DatabaseTable.class);
  }

  @Override
  protected Map<String, Object> doProcess(@NonNull DatabaseTable context) {
    return Map.of(
      "table", context,
      "columns", context.getColumns()
    );
  }
}
