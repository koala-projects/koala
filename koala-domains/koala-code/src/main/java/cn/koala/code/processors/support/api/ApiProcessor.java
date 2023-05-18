package cn.koala.code.processors.support.api;

import cn.koala.code.processors.AbstractContextProcessor;
import cn.koala.code.processors.support.TableHelper;
import cn.koala.database.DatabaseTable;
import cn.koala.database.DatabaseTableColumn;
import cn.koala.toolkit.WordHelper;
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

  public ApiProcessor() {
    this(TableHelper.TABLE_PREFIX);
  }

  public ApiProcessor(String tablePrefix) {
    super(DatabaseTable.class);
    this.tablePrefix = tablePrefix;
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

  /**
   * 加工权限代码
   * <p>
   * 将表名去除前缀, 并将格式从下划线转换为短横线
   *
   * @param table 数据库表
   * @return 权限代码
   */
  protected String processPermission(DatabaseTable table) {
    String tableName = table.getName();
    return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, tableName.replace(tablePrefix, ""));
  }

  protected ApiParameters processParameters(DatabaseTable context) {
    return processParameters(context.getColumns().stream()
      .filter(column -> !TableHelper.IGNORED_PARAMETER_COLUMNS.contains(column.getName()))
      .collect(Collectors.toList()));
  }

  protected ApiParameters processParameters(List<? extends DatabaseTableColumn> columns) {
    Optional<? extends DatabaseTableColumn> id = columns.stream()
      .filter(column -> TableHelper.ID_COLUMN.equals(column.getName()))
      .findFirst();
    Assert.isTrue(id.isPresent(), "数据表必须包含名为id的主键列");
    return ApiParameters.builder()
      .id(processParameter(id.get()))
      .others(columns.stream()
        .filter(column -> !TableHelper.ID_COLUMN.equals(column.getName()))
        .map(this::processParameter)
        .collect(Collectors.toList()))
      .build();
  }

  /**
   * 加工查询参数
   * <p>
   * 将数据库列转换为查询参数, 转换规则如下:
   * <p>
   * 名称: 列名格式从下划线转换为小驼峰
   * <p>
   * 描述: 列备注
   * <p>
   * 类型: 列类型转换为JSON类型
   *
   * @param column 数据库列
   * @return 查询参数
   */
  protected ApiParameter processParameter(DatabaseTableColumn column) {
    return ApiParameter.builder()
      .name(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, column.getName()))
      .description(column.getRemarks())
      .type(TableHelper.columnType2JsonType(column.getType()))
      .build();
  }
}
