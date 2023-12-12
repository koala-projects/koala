package cn.koala.codegen.support.entity;

import cn.koala.codegen.CodeGenContext;
import cn.koala.codegen.CodeGenContextProcessor;
import cn.koala.codegen.support.SimpleCodeGenContext;
import cn.koala.database.domain.DatabaseTable;
import cn.koala.database.domain.DatabaseTableColumn;
import com.google.common.base.CaseFormat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 实体代码生成上下文加工器
 *
 * @author Houtaroy
 */
public class EntityCodeGenContextProcessor implements CodeGenContextProcessor {

  private static final List<EntityValidationConverter> DEFAULT_VALIDATION_CONVERTERS = List.of(
    new NotNullEntityValidationConverter(),
    new MaxEntityValidationConverter(),
    new DigitsEntityValidationConverter(),
    new SizeEntityValidationConverter()
  );

  private static final Set<String> COLUMN_ABSTRACT_REQUIRED_NAMES = Set.of(
    "created_time",
    "is_deleted",
    "deleted_by",
    "deleted_time"
  );

  private static final Set<String> ABSTRACT_IGNORED_PROPERTY_NAMES = Set.of(
    "id",
    "sortIndex",
    "isEnabled",
    "isSystemic",
    "isDeleted",
    "createdBy",
    "createdTime",
    "lastModifiedBy",
    "lastModifiedTime",
    "deletedBy",
    "deletedTime"
  );

  private final List<EntityValidationConverter> validationConverters;

  public EntityCodeGenContextProcessor() {
    this(DEFAULT_VALIDATION_CONVERTERS);
  }

  public EntityCodeGenContextProcessor(List<EntityValidationConverter> validationConverters) {
    this.validationConverters = validationConverters;
  }

  @Override
  public CodeGenContext process(DatabaseTable table) {
    SimpleCodeGenContext result = new SimpleCodeGenContext(1);
    result.put("entity", Map.of(
      "abstractIgnoredPropertyNames", ABSTRACT_IGNORED_PROPERTY_NAMES,
      "isAbstract", processEntityAbstract(table),
      "validations", processEntityValidations(table)
    ));
    return result;
  }

  private boolean processEntityAbstract(DatabaseTable table) {
    return table.getColumns().stream()
      .map(DatabaseTableColumn::getName)
      .collect(Collectors.toSet())
      .containsAll(COLUMN_ABSTRACT_REQUIRED_NAMES);
  }

  private Map<String, List<EntityValidation>> processEntityValidations(DatabaseTable table) {
    Map<String, List<EntityValidation>> result = new HashMap<>(table.getColumns().size());
    for (DatabaseTableColumn column : table.getColumns()) {
      result.put(
        CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, column.getName()),
        processEntityValidation(column)
      );
    }
    return result;
  }

  protected List<EntityValidation> processEntityValidation(DatabaseTableColumn column) {
    return validationConverters.stream()
      .filter(converter -> converter.support(column))
      .map(converter -> converter.convert(column))
      .toList();
  }
}
