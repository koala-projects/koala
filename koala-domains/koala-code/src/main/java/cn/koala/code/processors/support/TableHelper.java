package cn.koala.code.processors.support;

import cn.koala.code.processors.support.entity.JavaType;
import cn.koala.database.DatabaseTable;
import cn.koala.database.DatabaseTableColumn;
import cn.koala.toolkit.mapping.StaticMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 领域名称抽象类
 *
 * @author Houtaroy
 */
public abstract class TableHelper {
  public static final String TABLE_PREFIX = "t_";
  public static final String TABLE_REMARKS_SUFFIX = "表";
  public static final String ID_COLUMN = "id";
  public static final String SORT_COLUMN = "sort_index";
  public static final Set<String> STATE_COLUMNS = Set.of("is_enabled", "is_systemic", "is_deleted");
  public static final Set<String> AUDIT_COLUMNS = Set.of("created_by", "created_time", "last_modified_by", "last_modified_time", "deleted_by", "deleted_time");
  public static final Set<String> IGNORED_PARAMETER_COLUMNS = Set.of("sort_index", "is_deleted", "last_modified_by", "last_modified_time", "deleted_by", "deleted_time");
  public static final List<String> IGNORED_VALIDATION_COLUMNS;
  public static final String STATE_PROPERTY_TYPE = "YesNo";

  public static final Map<Integer, String> JAVA_TYPE_MAP;
  public static final Map<Integer, String> JSON_TYPE_MAP;

  static {
    IGNORED_VALIDATION_COLUMNS = new ArrayList<>();
    IGNORED_VALIDATION_COLUMNS.add(ID_COLUMN);
    IGNORED_VALIDATION_COLUMNS.addAll(STATE_COLUMNS);
    IGNORED_VALIDATION_COLUMNS.addAll(AUDIT_COLUMNS);
  }

  static {
    JAVA_TYPE_MAP = new HashMap<>(12);
    JAVA_TYPE_MAP.put(-6, JavaType.Integer.getName());
    JAVA_TYPE_MAP.put(5, JavaType.Integer.getName());
    JAVA_TYPE_MAP.put(4, JavaType.Integer.getName());
    JAVA_TYPE_MAP.put(-5, JavaType.Long.getName());
    JAVA_TYPE_MAP.put(6, JavaType.Float.getName());
    JAVA_TYPE_MAP.put(8, JavaType.Double.getName());
    JAVA_TYPE_MAP.put(2, JavaType.Double.getName());
    JAVA_TYPE_MAP.put(3, JavaType.Double.getName());
    JAVA_TYPE_MAP.put(91, JavaType.Date.getName());
    JAVA_TYPE_MAP.put(92, JavaType.Time.getName());
    JAVA_TYPE_MAP.put(93, JavaType.Date.getName());
    JAVA_TYPE_MAP.put(16, JavaType.Boolean.getName());
  }

  static {
    JSON_TYPE_MAP = new HashMap<>(12);
    JSON_TYPE_MAP.put(-6, "integer");
    JSON_TYPE_MAP.put(5, "integer");
    JSON_TYPE_MAP.put(4, "integer");
    JSON_TYPE_MAP.put(-5, "integer");
    JSON_TYPE_MAP.put(6, "number");
    JSON_TYPE_MAP.put(8, "number");
    JSON_TYPE_MAP.put(2, "number");
    JSON_TYPE_MAP.put(3, "number");
    JSON_TYPE_MAP.put(91, "date-time");
    JSON_TYPE_MAP.put(92, "time");
    JSON_TYPE_MAP.put(93, "date-time");
    JSON_TYPE_MAP.put(16, "boolean");
  }

  public static final StaticMapping<Integer, String> JAVA_TYPE_MAPPING = new StaticMapping<>(JAVA_TYPE_MAP);
  public static final StaticMapping<Integer, String> JSON_TYPE_MAPPING = new StaticMapping<>(JSON_TYPE_MAP);

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

  public static boolean isColumnValidatable(DatabaseTableColumn column) {
    return !IGNORED_VALIDATION_COLUMNS.contains(column.getName());
  }

  public static String columnType2JsonType(Integer columnType) {
    return JSON_TYPE_MAPPING.map(columnType, "string");
  }

  public static String columnType2JavaType(Integer columnType) {
    return JAVA_TYPE_MAPPING.map(columnType, JavaType.String.getName());
  }
}
