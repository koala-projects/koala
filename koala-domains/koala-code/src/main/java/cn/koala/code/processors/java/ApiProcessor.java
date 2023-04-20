package cn.koala.code.processors.java;

import cn.koala.code.processors.AbstractContextProcessor;
import cn.koala.code.processors.java.converter.JsonTypeConverter;
import cn.koala.database.DatabaseTable;
import cn.koala.database.DatabaseTableColumn;
import cn.koala.toolkit.converter.Converter;
import cn.koala.toolkit.word.WordHelper;
import com.google.common.base.CaseFormat;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 接口上下文加工器
 *
 * @author Houtaroy
 */
public class ApiProcessor extends AbstractContextProcessor<DatabaseTable> {

  private final String tablePrefix;
  private final Converter<Integer, String> typeConverter;

  public ApiProcessor() {
    this(KoalaHelper.TABLE_PREFIX);
  }

  public ApiProcessor(String tablePrefix) {
    this(tablePrefix, new JsonTypeConverter());
  }

  public ApiProcessor(String tablePrefix, Converter<Integer, String> typeConverter) {
    super(DatabaseTable.class);
    this.tablePrefix = tablePrefix;
    this.typeConverter = typeConverter;
  }

  @Override
  protected Map<String, Object> doProcess(DatabaseTable context) {
    return Map.of("api", processApiContext(context));
  }

  protected ApiContext processApiContext(DatabaseTable context) {
    String permission = processPermission(context);
    return ApiContext.builder()
      .path(WordHelper.plural(permission))
      .permission(permission)
      .parameters(processParameters(context))
      .build();
  }

  protected String processPermission(DatabaseTable table) {
    String tableName = table.getName();
    return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, tableName.replace(tablePrefix, ""));
  }

  protected ApiContext.Parameters processParameters(DatabaseTable context) {
    return processParameters(context.getColumns().stream()
      .filter(column -> !KoalaHelper.IGNORED_PARAMETER_COLUMNS.contains(column.getName()))
      .collect(Collectors.toList()));
  }

  protected ApiContext.Parameters processParameters(List<? extends DatabaseTableColumn> columns) {
    Optional<? extends DatabaseTableColumn> id = columns.stream()
      .filter(column -> KoalaHelper.ID_COLUMN.equals(column.getName()))
      .findFirst();
    Assert.isTrue(id.isPresent(), "数据表必须包含名为id的主键列");
    return ApiContext.Parameters.builder()
      .id(processParameter(id.get()))
      .others(columns.stream()
        .filter(column -> !KoalaHelper.ID_COLUMN.equals(column.getName()))
        .map(this::processParameter)
        .collect(Collectors.toList()))
      .build();
  }

  protected Parameter processParameter(DatabaseTableColumn column) {
    return Parameter.builder()
      .name(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, column.getName()))
      .description(column.getRemarks())
      .type(typeConverter.convert(column.getType()))
      .build();
  }
}
