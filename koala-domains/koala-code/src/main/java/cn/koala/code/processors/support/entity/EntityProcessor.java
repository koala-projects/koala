package cn.koala.code.processors.support.entity;

import cn.koala.code.processors.AbstractContextProcessor;
import cn.koala.code.processors.support.TableHelper;
import cn.koala.database.DatabaseTable;
import cn.koala.database.DatabaseTableColumn;
import com.google.common.base.CaseFormat;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 实体上下文加工器
 *
 * @author Houtaroy
 */
public class EntityProcessor extends AbstractContextProcessor<DatabaseTable> {

  private static final Set<String> DEFAULT_IMPORTS = Set.of(
    "lombok.Data",
    "lombok.NoArgsConstructor",
    "lombok.experimental.SuperBuilder",
    "cn.koala.persist.domain.Persistable",
    "io.swagger.v3.oas.annotations.media.Schema"
  );

  private static final List<EntityValidationConverter> DEFAULT_VALIDATION_CONVERTERS = List.of(
    new NotNullValidationConverter(),
    new MaxValidationConverter(),
    new DigitsValidationConverter(),
    new SizeValidationConverter()
  );

  private final List<EntityValidationConverter> validationConverters = DEFAULT_VALIDATION_CONVERTERS;

  public EntityProcessor() {
    super(DatabaseTable.class);
  }

  @Override
  protected Map<String, Object> doProcess(DatabaseTable context) {
    return Map.of("entity", processEntityContext(context));
  }

  protected EntityContext processEntityContext(DatabaseTable context) {
    EntityProperties properties = processProperties(context);
    Set<String> imports = processImports(context);
    imports.addAll(processImports(properties.getOthers()));
    return EntityContext.builder()
      .imports(imports.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new)))
      .interfaces(processInterfaces(context))
      .properties(properties)
      .build();
  }

  protected Set<String> processImports(DatabaseTable context) {
    Set<String> result = new HashSet<>(DEFAULT_IMPORTS);
    if (TableHelper.isAuditable(context)) {
      result.add("cn.koala.persist.domain.Auditable");
    }
    if (TableHelper.isSortable(context)) {
      result.add("cn.koala.persist.domain.Sortable");
    }
    if (TableHelper.isStateful(context)) {
      result.add("cn.koala.persist.domain.Stateful");
      result.add("cn.koala.persist.domain.YesNo");
    }
    return result;
  }

  protected Set<String> processImports(List<EntityProperty> properties) {
    Set<String> result = new HashSet<>();
    if (properties.stream().anyMatch(property -> JavaType.Date.getName().equals(property.getType()))) {
      result.add("java.util.Date");
    }
    result.addAll(properties.stream()
      .flatMap(property -> property.getValidations().stream())
      .map(validation -> "jakarta.validation.constraints.%s".formatted(validation.getName()))
      .collect(Collectors.toSet()));
    result.addAll(properties.stream()
      .flatMap(property -> property.getValidations().stream())
      .flatMap(validation -> validation.getGroups().stream())
      .map("cn.koala.validation.group.%s"::formatted)
      .collect(Collectors.toSet()));
    return result;
  }

  protected Set<String> processInterfaces(DatabaseTable context) {
    Set<String> result = new HashSet<>();
    if (TableHelper.isSortable(context)) {
      result.add("Sortable");
    }
    if (TableHelper.isStateful(context)) {
      result.add("Stateful");
    }
    if (TableHelper.isAuditable(context)) {
      result.add("Auditable<Long>");
    }
    return result;
  }

  protected EntityProperties processProperties(DatabaseTable context) {
    Optional<EntityProperty> id = context.getColumns().stream()
      .filter(column -> TableHelper.ID_COLUMN.equals(column.getName()))
      .findFirst()
      .map(this::processProperty);
    Assert.isTrue(id.isPresent(), "数据表必须包含名为id的主键列");
    return EntityProperties.builder()
      .id(id.get())
      .others(context.getColumns().stream()
        .filter(column -> !TableHelper.ID_COLUMN.equals(column.getName()))
        .map(this::processProperty)
        .collect(Collectors.toList()))
      .build();
  }

  protected EntityProperty processProperty(DatabaseTableColumn column) {
    return EntityProperty.builder()
      .name(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, column.getName()))
      .description(column.getRemarks())
      .type(processJavaType(column))
      .validations(processValidations(column))
      .build();
  }

  protected String processJavaType(DatabaseTableColumn column) {
    return TableHelper.STATE_COLUMNS.contains(column.getName())
      ? TableHelper.STATE_PROPERTY_TYPE
      : TableHelper.columnType2JavaType(column.getType());
  }

  protected List<EntityValidation> processValidations(DatabaseTableColumn column) {
    if (TableHelper.isColumnValidatable(column)) {
      return validationConverters.stream()
        .filter(converter -> converter.support(column))
        .map(converter -> converter.convert(column))
        .toList();
    }
    return Collections.emptyList();
  }
}
