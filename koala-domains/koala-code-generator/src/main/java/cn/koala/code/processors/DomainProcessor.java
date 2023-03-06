package cn.koala.code.processors;

import cn.koala.code.database.Column;
import cn.koala.code.database.JavaTypeConverter;
import cn.koala.code.database.JsonTypeConverter;
import cn.koala.code.database.Table;
import cn.koala.toolkit.word.WordHelper;
import com.google.common.base.CaseFormat;
import org.springframework.util.Assert;

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
public class DomainProcessor extends BaseContextProcessor<Table> {

  private static final String SORT_COLUMN_NAME = "sort_index";
  private static final Set<String> STATE_COLUMN_NAMES = Set.of("is_enable", "is_system", "is_delete");
  private static final Set<String> AUDIT_COLUMN_NAMES = Set.of("create_user_id", "create_time", "last_update_user_id", "last_update_time", "delete_user_id", "delete_time");
  private static final List<String> IGNORED_PARAMETER_NAMES = List.of("sortIndex", "isDelete", "deleteUserId", "deleteTime");
  private static final JavaTypeConverter JAVA_TYPE_CONVERTER = new JavaTypeConverter();
  private static final JsonTypeConverter JSON_TYPE_CONVERTER = new JsonTypeConverter();
  private final String tablePrefix;

  public DomainProcessor() {
    this("");
  }

  public DomainProcessor(String tablePrefix) {
    super(Table.class);
    this.tablePrefix = tablePrefix;
  }

  @Override
  protected Map<String, Object> doProcess(Table context) {
    DomainProperty id = getId(context);
    Assert.notNull(id, "表[%s]不存在列名为id的主键".formatted(context.getTableName()));
    String permission = getPermission(context);
    List<DomainProperty> properties = getProperties(context);
    return Map.of(
      "name", getDomainName(context),
      "description", getDomainDescription(context),
      "properties", properties,
      "implements", getImplements(context),
      "id", id,
      "api", WordHelper.plural(permission),
      "permission", permission,
      "parameters", properties.stream().filter(this::isParameter).toList()
    );
  }

  protected String getDomainName(Table table) {
    String tableName = table.getTableName();
    return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.replace(tablePrefix, ""));
  }

  protected String getDomainDescription(Table table) {
    String remarks = table.getRemarks();
    return remarks.endsWith("表") ? remarks.substring(0, remarks.length() - 1) : remarks;
  }

  protected List<DomainProperty> getProperties(Table table) {
    return table.getColumns().stream().map(this::getProperty).toList();
  }

  protected DomainProperty getProperty(Column column) {
    return new DomainProperty(
      CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, column.getColumnName()),
      column.getRemarks(),
      getPropertyJavaType(column),
      JSON_TYPE_CONVERTER.convert(column.getDataType())
    );
  }

  protected String getPropertyJavaType(Column column) {
    return STATE_COLUMN_NAMES.contains(column.getColumnName()) ? "YesNo" : JAVA_TYPE_CONVERTER.convert(column.getDataType());
  }

  protected Set<String> getImplements(Table table) {
    Set<String> result = new HashSet<>(3);
    Set<String> columnNames = table.getColumns().stream().map(Column::getColumnName).collect(Collectors.toSet());
    if (columnNames.contains(SORT_COLUMN_NAME)) {
      result.add("SortModel");
    }
    if (columnNames.containsAll(STATE_COLUMN_NAMES)) {
      result.add("StateModel");
    }
    if (columnNames.containsAll(AUDIT_COLUMN_NAMES)) {
      result.add("AuditModel<Long>");
    }
    return result;
  }

  protected DomainProperty getId(Table table) {
    return table.getColumns().stream().filter(this::isPrimaryColumnId).findFirst().map(this::getProperty).orElse(null);
  }

  protected boolean isPrimaryColumnId(Column column) {
    return "id".equals(column.getColumnName()) && column.getIsPrimaryKey();
  }

  protected String getPermission(Table table) {
    String tableName = table.getTableName();
    return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, tableName.replace(tablePrefix, ""));
  }

  protected boolean isParameter(DomainProperty property) {
    return !IGNORED_PARAMETER_NAMES.contains(property.getName());
  }
}
