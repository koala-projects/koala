package cn.koala.code.processors.java;

import cn.koala.database.DatabaseTable;
import cn.koala.database.DatabaseTableColumn;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 领域名称抽象类
 *
 * @author Houtaroy
 */
public abstract class KoalaHelper {
  public static final String TABLE_PREFIX = "t_";
  public static final String ID_COLUMN = "id";
  public static final String SORT_COLUMN = "sort_index";
  public static final Set<String> STATE_COLUMNS = Set.of("is_enabled", "is_systemic", "is_deleted");
  public static final Set<String> AUDIT_COLUMNS = Set.of("created_by", "created_time", "last_modified_by", "last_modified_time", "deleted_by", "deleted_time");
  public static final Set<String> IGNORED_PARAMETER_COLUMNS = Set.of("sort_index", "is_deleted", "last_modified_by", "last_modified_time", "deleted_by", "deleted_time");
  public static final List<String> IGNORED_VALIDATION_COLUMNS;
  public static final String STATE_PROPERTY_TYPE = "YesNo";

  static {
    IGNORED_VALIDATION_COLUMNS = new ArrayList<>();
    IGNORED_VALIDATION_COLUMNS.add(ID_COLUMN);
    IGNORED_VALIDATION_COLUMNS.addAll(STATE_COLUMNS);
    IGNORED_VALIDATION_COLUMNS.addAll(AUDIT_COLUMNS);
  }

  public static boolean isAuditable(DatabaseTable table) {
    return table.getColumns().stream()
      .map(DatabaseTableColumn::getName)
      .collect(Collectors.toSet())
      .containsAll(AUDIT_COLUMNS);
  }

  public static boolean isSortable(DatabaseTable table) {
    return table.getColumns().stream()
      .map(DatabaseTableColumn::getName)
      .anyMatch(SORT_COLUMN::equals);
  }

  public static boolean isStateful(DatabaseTable table) {
    return table.getColumns().stream()
      .map(DatabaseTableColumn::getName)
      .collect(Collectors.toSet())
      .containsAll(STATE_COLUMNS);
  }
}
