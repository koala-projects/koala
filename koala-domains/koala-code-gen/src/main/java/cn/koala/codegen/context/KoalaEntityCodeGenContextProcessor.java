package cn.koala.codegen.context;

import cn.koala.codegen.context.validation.JakartaValidationBuilder;
import cn.koala.codegen.utils.CodeGenNames;
import cn.koala.database.domain.DatabaseTable;
import cn.koala.database.domain.DatabaseTableColumn;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 考拉实体代码生成上下文加工器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class KoalaEntityCodeGenContextProcessor implements CodeGenContextProcessor {

  private final List<JakartaValidationBuilder> validationBuilders;

  @Override
  public CodeGenContext process(CodeGenContext context) {
    boolean auditable = context.get("auditable");
    boolean sortable = context.get("sortable");
    boolean enableable = context.get("enableable");
    boolean systemic = context.get("systemic");
    context.put("abstract", auditable && sortable && enableable && systemic);
    context.put("imports", processKoalaEntityImports(auditable, sortable, enableable, systemic));
    context.put(
      "implements",
      processKoalaEntityImplements(context.get("id"), auditable, sortable, enableable, systemic)
    );
    KoalaCodeGenContext koala = context.get("koala");
    if (koala == null) {
      koala = new KoalaCodeGenContext();
    }
    koala.setProperties(processKoalaEntityProperties(context));
    context.put("koala", koala);
    return context;
  }

  private Set<String> processKoalaEntityImports(boolean auditable, boolean sortable, boolean enableable,
                                                boolean systemic) {

    Set<String> result = new LinkedHashSet<>();
    result.add(auditable ? "cn.koala.mybatis.domain.Auditable" : "org.springframework.data.domain.Persistable");
    if (enableable) {
      result.add("cn.koala.mybatis.domain.Enableable");
    }
    if (sortable) {
      result.add("cn.koala.mybatis.domain.Sortable");
    }
    if (systemic) {
      result.add("cn.koala.mybatis.domain.Systemic");
    }
    return result;
  }

  private Set<String> processKoalaEntityImplements(DomainProperty id, boolean auditable, boolean sortable,
                                                   boolean enableable, boolean systemic) {

    Set<String> result = new HashSet<>();
    result.add((auditable ? "Auditable<Long, %s>" : "Persistable<%s>").formatted(id.getType().get("java")));
    if (sortable) {
      result.add("Sortable");
    }
    if (enableable) {
      result.add("Enableable");
    }
    if (systemic) {
      result.add("Systemic");
    }
    return result;
  }

  private List<KoalaEntityProperty> processKoalaEntityProperties(CodeGenContext context) {
    DatabaseTable table = context.getTable();
    Map<String, DatabaseTableColumn> columns = table.getColumns().stream()
      .collect(Collectors.toMap(DatabaseTableColumn::getName, column -> column));
    List<DomainProperty> properties = context.get("properties");
    if (context.get("abstract")) {
      properties = properties.stream()
        .filter(property -> !CodeGenNames.COLUMN_AUDITABLE.contains(property.getName().getSnake().getSingular()))
        .filter(property -> !CodeGenNames.COLUMN_SORTABLE.equals(property.getName().getSnake().getSingular()))
        .filter(property -> !CodeGenNames.COLUMN_ENABLEABLE.equals(property.getName().getSnake().getSingular()))
        .filter(property -> !CodeGenNames.COLUMN_SYSTEMIC.equals(property.getName().getSnake().getSingular()))
        .toList();
    }
    return properties.stream().map(property -> {
      KoalaEntityProperty result = KoalaEntityProperty.from(property);
      DatabaseTableColumn column = columns.get(property.getName().getSnake().getSingular());
      result.setValidations(processKoalaEntityPropertyValidations(column));
      return result;
    }).toList();
  }

  private List<String> processKoalaEntityPropertyValidations(DatabaseTableColumn column) {
    return validationBuilders.stream()
      .filter(builder -> builder.support(column))
      .map(builder -> builder.build(column))
      .toList();
  }
}
