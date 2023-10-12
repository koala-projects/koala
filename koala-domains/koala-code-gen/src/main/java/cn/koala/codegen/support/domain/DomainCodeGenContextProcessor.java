package cn.koala.codegen.support.domain;

import cn.koala.codegen.CodeGenContext;
import cn.koala.codegen.CodeGenContextProcessor;
import cn.koala.codegen.support.SimpleCodeGenContext;
import cn.koala.codegen.support.TableHelper;
import cn.koala.database.DatabaseTable;
import cn.koala.database.DatabaseTableColumn;
import cn.koala.toolkit.name.Name;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 领域代码生成上下文加工器
 *
 * @author Houtaroy
 */
public class DomainCodeGenContextProcessor implements CodeGenContextProcessor {

  private static final List<NamedDomainPropertyTypeConverter> DEFAULT_TYPE_CONVERTERS = List.of(
    new NamedDomainPropertyTypeConverter("java", TableHelper::determinedJavaTypeName),
    new NamedDomainPropertyTypeConverter("json", TableHelper::determinedJsonTypeName),
    new NamedDomainPropertyTypeConverter("ts", TableHelper::determinedTypeScriptTypeName),
    new NamedDomainPropertyTypeConverter("vben", TableHelper::determinedVbenComponentTypeName)
  );
  private static final String TABLE_REMARKS_SUFFIX = "表";
  private static final String COLUMN_ID_NAME = "id";

  private final String tablePrefix;
  private final List<NamedDomainPropertyTypeConverter> typeConverters;

  public DomainCodeGenContextProcessor(String tablePrefix) {
    this(tablePrefix, DEFAULT_TYPE_CONVERTERS);
  }

  public DomainCodeGenContextProcessor(String tablePrefix, List<NamedDomainPropertyTypeConverter> typeConverters) {
    this.tablePrefix = tablePrefix;
    this.typeConverters = typeConverters;
  }

  @Override
  public CodeGenContext process(DatabaseTable table) {
    SimpleCodeGenContext result = new SimpleCodeGenContext(3);
    result.put("name", processDomainName(table));
    result.put("description", processDomainDescription(table));
    result.put("id", processDomainId(table));
    result.put("properties", processDomainProperties(table));
    return result;
  }

  private Name processDomainName(DatabaseTable table) {
    return Name.fromSnakeSingular(table.getName().replace(tablePrefix, ""));
  }

  private String processDomainDescription(DatabaseTable table) {
    String result = table.getRemarks();
    if (result.endsWith(TABLE_REMARKS_SUFFIX)) {
      result = result.substring(0, result.length() - 1);
    }
    return result;
  }

  private DomainProperty processDomainId(DatabaseTable table) {
    return table.getColumns().stream()
      .filter(column -> COLUMN_ID_NAME.equals(column.getName()))
      .findFirst()
      .map(this::processDomainProperty)
      .orElseThrow(() -> new IllegalArgumentException("数据表必须包含名为id的主键列"));
  }

  private List<DomainProperty> processDomainProperties(DatabaseTable table) {
    return table.getColumns().stream()
      .filter(column -> !COLUMN_ID_NAME.equals(column.getName()))
      .map(this::processDomainProperty)
      .toList();
  }

  private DomainProperty processDomainProperty(DatabaseTableColumn column) {
    return DomainProperty.builder()
      .name(Name.fromSnakeSingular(column.getName()))
      .description(column.getRemarks())
      .type(processDomainPropertyType(column))
      .build();
  }

  private Map<String, String> processDomainPropertyType(DatabaseTableColumn column) {
    return typeConverters.stream()
      .collect(Collectors.toMap(NamedDomainPropertyTypeConverter::getName, converter -> converter.convert(column)));
  }
}
