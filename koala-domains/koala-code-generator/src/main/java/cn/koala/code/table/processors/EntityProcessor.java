package cn.koala.code.table.processors;

import cn.koala.code.BaseContextProcessor;
import cn.koala.code.table.TableContext;
import cn.koala.toolkit.jdbc.Column;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 实体类上下文加工器
 *
 * @author Houtaroy
 */
public class EntityProcessor extends BaseContextProcessor<TableContext> {

  private static final String SORT_COLUMN_NAME = "sort_index";
  private static final Set<String> STATE_COLUMN_NAMES = Set.of("is_enable", "is_system", "is_delete");
  private static final Set<String> AUDIT_COLUMN_NAMES = Set.of("create_user_id", "create_time", "last_update_user_id", "last_update_time", "delete_user_id", "delete_time");

  public EntityProcessor() {
    super(TableContext.class);
  }

  @Override
  protected Map<String, Object> doProcess(TableContext context) {
    List<Column> columns = context.getColumns();
    Set<String> columnNames = columns.stream().map(Column::getColumnName).collect(Collectors.toSet());
    boolean sortModel = columnNames.contains(SORT_COLUMN_NAME);
    boolean stateModel = columnNames.containsAll(STATE_COLUMN_NAMES);
    boolean auditModel = columnNames.containsAll(AUDIT_COLUMN_NAMES);
    return Map.of(
      "propertyColumns", filterColumn(columns, sortModel, stateModel, auditModel),
      "sortModel", sortModel,
      "stateModel", stateModel,
      "auditModel", auditModel
    );
  }

  protected List<Column> filterColumn(List<Column> columns, boolean sortModel, boolean stateModel, boolean auditModel) {
    List<Column> result = columns;
    Set<String> filterNames = new HashSet<>();
    if (sortModel) {
      filterNames.add(SORT_COLUMN_NAME);
    }
    if (stateModel) {
      filterNames.addAll(STATE_COLUMN_NAMES);
    }
    if (auditModel) {
      filterNames.addAll(AUDIT_COLUMN_NAMES);
    }
    if (!filterNames.isEmpty()) {
      result = filterColumnByNames(result, filterNames);
    }
    return result;
  }

  protected List<Column> filterColumnByNames(List<Column> columns, Set<String> names) {
    return columns.stream().filter(column -> !names.contains(column.getColumnName())).toList();
  }
}
