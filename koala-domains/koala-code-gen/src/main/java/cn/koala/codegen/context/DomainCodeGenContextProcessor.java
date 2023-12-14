package cn.koala.codegen.context;

import cn.koala.codegen.context.type.JdbcTypeMapping;
import cn.koala.codegen.name.Name;
import cn.koala.codegen.name.NameFactory;
import cn.koala.codegen.utils.CodeGenNames;
import cn.koala.codegen.utils.CodeGenUtils;
import cn.koala.database.domain.DatabaseTable;
import cn.koala.database.domain.DatabaseTableColumn;
import cn.koala.exception.BusinessException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class DomainCodeGenContextProcessor implements CodeGenContextProcessor {

  private final String tablePrefix;

  private final String tableRemarksSuffix;

  private final NameFactory nameFactory;

  private final List<JdbcTypeMapping> jdbcTypeMappings;

  @Override
  public CodeGenContext process(CodeGenContext context) {
    DatabaseTable table = context.getTable();
    Set<String> columnNames = table.getColumns().stream().map(DatabaseTableColumn::getName).collect(Collectors.toSet());
    context.put("name", processDomainName(table));
    context.put("description", processDomainDescription(table));
    context.put("id", processDomainId(table));
    context.put("properties", processDomainProperties(table));
    context.put("auditable", columnNames.containsAll(CodeGenNames.COLUMN_AUDITABLE));
    context.put("enableable", columnNames.contains(CodeGenNames.COLUMN_ENABLEABLE));
    context.put("sortable", columnNames.contains(CodeGenNames.COLUMN_SORTABLE));
    context.put("systemic", columnNames.contains(CodeGenNames.COLUMN_SYSTEMIC));
    return context;
  }

  private Name processDomainName(DatabaseTable table) {
    String snake = table.getName();
    if (snake.startsWith(tablePrefix)) {
      snake = snake.substring(tablePrefix.length());
    }
    return nameFactory.fromSnakeSingular(snake);
  }

  private String processDomainDescription(DatabaseTable table) {
    String result = table.getRemarks();
    if (result.endsWith(tableRemarksSuffix)) {
      result = result.substring(0, result.length() - tableRemarksSuffix.length());
    }
    return result;
  }

  private DomainProperty processDomainId(DatabaseTable table) {
    return table.getColumns().stream()
      .filter(CodeGenUtils::isId)
      .findFirst()
      .map(this::processDomainProperty)
      .orElseThrow(() -> new BusinessException("数据表必须包含名为id的主键列"));
  }

  private List<DomainProperty> processDomainProperties(DatabaseTable table) {
    return table.getColumns().stream()
      .filter(column -> !CodeGenUtils.isId(column))
      .map(this::processDomainProperty)
      .toList();
  }

  private DomainProperty processDomainProperty(DatabaseTableColumn column) {
    return DomainProperty.builder()
      .name(nameFactory.fromSnakeSingular(column.getName()))
      .description(column.getRemarks())
      .type(processDomainPropertyType(column))
      .build();
  }

  private Map<String, String> processDomainPropertyType(DatabaseTableColumn column) {
    return jdbcTypeMappings.stream()
      .collect(Collectors.toMap(JdbcTypeMapping::getName, mapping -> mapping.map(column.getType())));
  }
}
