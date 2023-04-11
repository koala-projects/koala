package cn.koala.code.processors;

import cn.koala.database.DatabaseTable;
import cn.koala.database.DatabaseTableColumn;
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
public class DomainProcessor extends BaseContextProcessor<DatabaseTable> {

  private static final String SORT_COLUMN_NAME = "sort_index";
  private static final Set<String> STATE_COLUMN_NAMES = Set.of("is_enabled", "is_systemic", "is_deleted");
  private static final Set<String> AUDIT_COLUMN_NAMES = Set.of("created_by", "created_time", "last_modified_by", "last_modified_time", "deleted_by", "deleted_time");
  private static final List<String> IGNORED_PARAMETER_NAMES = List.of("sortIndex", "isDeleted", "deletedBy", "deletedTime");
  private static final JavaTypeConverter JAVA_TYPE_CONVERTER = new JavaTypeConverter();
  private static final JsonTypeConverter JSON_TYPE_CONVERTER = new JsonTypeConverter();
  private final String tablePrefix;

  public DomainProcessor() {
    this("");
  }

  public DomainProcessor(String tablePrefix) {
    super(DatabaseTable.class);
    this.tablePrefix = tablePrefix;
  }

  @Override
  protected Map<String, Object> doProcess(DatabaseTable context) {
    DomainProperty id = getId(context);
    Assert.notNull(id, "表[%s]不存在列名为id的主键".formatted(context.getName()));
    String name = getDomainName(context);
    String permission = getPermission(context);
    List<DomainProperty> properties = getProperties(context);
    return Map.of(
      "name", name,
      "pluralName", WordHelper.plural(name),
      "description", getDomainDescription(context),
      "properties", properties,
      "implements", getImplements(context),
      "id", id,
      "api", WordHelper.plural(permission),
      "permission", permission,
      "parameters", properties.stream().filter(this::isParameter).toList()
    );
  }

  protected DomainProperty getId(DatabaseTable table) {
    return table.getColumns().stream().filter(this::isPrimaryColumnId).findFirst().map(this::getProperty).orElse(null);
  }

  protected boolean isPrimaryColumnId(DatabaseTableColumn column) {
    return "id".equals(column.getName()) && column.getIsPrimaryKey();
  }

  protected String getPermission(DatabaseTable table) {
    String tableName = table.getName();
    return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, tableName.replace(tablePrefix, ""));
  }

  protected List<DomainProperty> getProperties(DatabaseTable table) {
    return table.getColumns().stream().map(this::getProperty).toList();
  }

  protected DomainProperty getProperty(DatabaseTableColumn column) {
    return new DomainProperty(
      CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, column.getName()),
      column.getRemarks(),
      getPropertyJavaType(column),
      JSON_TYPE_CONVERTER.convert(column.getType())
    );
  }

  protected String getPropertyJavaType(DatabaseTableColumn column) {
    return STATE_COLUMN_NAMES.contains(column.getName()) ? "YesNo" : JAVA_TYPE_CONVERTER.convert(column.getType());
  }

  protected String getDomainName(DatabaseTable table) {
    String tableName = table.getName();
    return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.replace(tablePrefix, ""));
  }

  protected String getDomainDescription(DatabaseTable table) {
    String remarks = table.getRemarks();
    return remarks.endsWith("表") ? remarks.substring(0, remarks.length() - 1) : remarks;
  }

  protected Set<String> getImplements(DatabaseTable table) {
    Set<String> result = new HashSet<>(3);
    Set<String> columnNames = table.getColumns().stream().map(DatabaseTableColumn::getName).collect(Collectors.toSet());
    if (columnNames.contains(SORT_COLUMN_NAME)) {
      result.add("Sortable");
    }
    if (columnNames.containsAll(STATE_COLUMN_NAMES)) {
      result.add("Stateful");
    }
    if (columnNames.containsAll(AUDIT_COLUMN_NAMES)) {
      result.add("Auditable<Long>");
    }
    return result;
  }

  protected boolean isParameter(DomainProperty property) {
    return !IGNORED_PARAMETER_NAMES.contains(property.getName());
  }
}
