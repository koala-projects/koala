package cn.koala.code.processors.java;

import cn.koala.code.processors.AbstractContextProcessor;
import cn.koala.code.processors.java.converter.JavaType;
import cn.koala.code.processors.java.converter.JavaTypeConverter;
import cn.koala.database.DatabaseTable;
import cn.koala.database.DatabaseTableColumn;
import cn.koala.toolkit.converter.Converter;
import com.google.common.base.CaseFormat;
import org.springframework.util.Assert;

import java.util.ArrayList;
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

  private final Converter<Integer, String> typeConverter;

  public EntityProcessor() {
    this(new JavaTypeConverter());
  }

  public EntityProcessor(Converter<Integer, String> typeConverter) {
    super(DatabaseTable.class);
    this.typeConverter = typeConverter;
  }

  @Override
  protected Map<String, Object> doProcess(DatabaseTable context) {
    return Map.of("entity", processEntityContext(context));
  }

  protected EntityContext processEntityContext(DatabaseTable context) {
    Set<String> imports = processImports(context);
    EntityContext.Properties properties = processProperties(context);
    imports.addAll(processImports(properties.getOthers()));
    return EntityContext.builder()
      .imports(imports.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new)))
      .interfaces(processInterfaces(context))
      .properties(processProperties(context))
      .build();
  }

  protected Set<String> processImports(DatabaseTable context) {
    Set<String> result = new HashSet<>();
    result.add("cn.koala.persist.domain.Persistable");
    result.add("io.swagger.v3.oas.annotations.media.Schema");
    if (KoalaHelper.isAuditable(context)) {
      result.add("cn.koala.persist.domain.Auditable");
    }
    if (KoalaHelper.isSortable(context)) {
      result.add("cn.koala.persist.domain.Sortable");
    }
    if (KoalaHelper.isStateful(context)) {
      result.add("cn.koala.persist.domain.Stateful");
      result.add("cn.koala.persist.domain.YesNo");
    }
    return result;
  }

  protected Set<String> processImports(List<Property> properties) {
    Set<String> result = properties.stream()
      .flatMap(property -> property.getValidations().stream())
      .map(validation -> "jakarta.validation.constraints.%s".formatted(validation.getName()))
      .collect(Collectors.toSet());
    result.addAll(properties.stream()
      .flatMap(property -> property.getValidations().stream())
      .flatMap(validation -> validation.getGroups().stream())
      .map("cn.koala.validation.group.%s"::formatted)
      .collect(Collectors.toSet()));
    return result;
  }

  protected Set<String> processInterfaces(DatabaseTable context) {
    Set<String> result = new HashSet<>();
    if (KoalaHelper.isSortable(context)) {
      result.add("Sortable");
    }
    if (KoalaHelper.isStateful(context)) {
      result.add("Stateful");
    }
    if (KoalaHelper.isAuditable(context)) {
      result.add("Auditable<Long>");
    }
    return result;
  }

  protected EntityContext.Properties processProperties(DatabaseTable context) {
    Optional<Property> id = context.getColumns().stream()
      .filter(column -> KoalaHelper.ID_COLUMN.equals(column.getName()))
      .findFirst()
      .map(this::processProperty);
    Assert.isTrue(id.isPresent(), "数据表必须包含名为id的主键列");
    return EntityContext.Properties.builder()
      .id(id.get())
      .others(context.getColumns().stream()
        .filter(column -> !KoalaHelper.ID_COLUMN.equals(column.getName()))
        .map(this::processProperty)
        .collect(Collectors.toList()))
      .build();
  }

  protected Property processProperty(DatabaseTableColumn column) {
    return Property.builder()
      .name(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, column.getName()))
      .description(column.getRemarks())
      .type(processJavaType(column))
      .validations(processValidations(column))
      .build();
  }

  protected String processJavaType(DatabaseTableColumn column) {
    return KoalaHelper.STATE_COLUMNS.contains(column.getName())
      ? KoalaHelper.STATE_PROPERTY_TYPE
      : typeConverter.convert(column.getType());
  }

  protected List<Property.Validation> processValidations(DatabaseTableColumn column) {
    List<Property.Validation> result = new ArrayList<>();
    if (isValidationColumn(column)) {
      if (!column.getIsNullable()) {
        result.add(processValidationNotNull(column));
      }
      if (column.getSize() > 0) {
        String type = processJavaType(column);
        if (JavaType.Integer.getName().equals(type) || JavaType.Long.getName().equals(type)) {
          result.add(processValidationMax(column));
        }
        if (JavaType.Float.getName().equals(type) || JavaType.Double.getName().equals(type)) {
          result.add(processValidationDigits(column));
        }
        if (JavaType.String.getName().equals(type)) {
          result.add(processValidationSize(column));
        }
      }
    }
    return result;
  }

  protected boolean isValidationColumn(DatabaseTableColumn column) {
    return !KoalaHelper.IGNORED_VALIDATION_COLUMNS.contains(column.getName());
  }

  protected Property.Validation processValidationNotNull(DatabaseTableColumn column) {
    return new Property.Validation(
      JavaType.String.getName().equals(processJavaType(column)) ? "NotBlank" : "NotNull",
      Map.of(),
      "%s不允许为空".formatted(column.getRemarks()),
      List.of("Add")
    );
  }

  protected Property.Validation processValidationMax(DatabaseTableColumn column) {
    Long value = processValidationMaxValue(column);
    return new Property.Validation(
      "Max",
      Map.of("value", value),
      "%s最大值不能大于%d".formatted(column.getRemarks(), value),
      List.of("Add")
    );
  }

  protected Long processValidationMaxValue(DatabaseTableColumn column) {
    return (long) Math.pow(10, Math.min(column.getSize(), 9)) - 1;
  }

  protected Property.Validation processValidationDigits(DatabaseTableColumn column) {
    return new Property.Validation(
      "Digits",
      Map.of("integer", column.getSize(), "fraction", column.getDecimalDigits()),
      "%s必须为小数，整数为%d位，小数为%d位".formatted(column.getRemarks(), column.getSize(), column.getDecimalDigits()),
      List.of("Add")
    );
  }

  protected Property.Validation processValidationSize(DatabaseTableColumn column) {
    return new Property.Validation(
      "Size",
      Map.of("max", column.getSize()),
      "%s长度不能大于%d".formatted(column.getRemarks(), column.getSize()),
      List.of("Add")
    );
  }
}
