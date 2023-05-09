package cn.koala.code.processors.support.domain;

import cn.koala.code.processors.AbstractContextProcessor;
import cn.koala.code.processors.support.TableHelper;
import cn.koala.database.DatabaseTable;
import cn.koala.toolkit.word.WordHelper;
import com.google.common.base.CaseFormat;

import java.util.Map;

/**
 * 领域名称上下文处理器
 *
 * @author Houtaroy
 */
public class DomainNameProcessor extends AbstractContextProcessor<DatabaseTable> {

  private final String tablePrefix;

  public DomainNameProcessor() {
    this(TableHelper.TABLE_PREFIX);
  }

  public DomainNameProcessor(String tablePrefix) {
    super(DatabaseTable.class);
    this.tablePrefix = tablePrefix;
  }

  @Override
  protected Map<String, Object> doProcess(DatabaseTable context) {
    String name = processDomainName(context);
    return Map.of(
      "name", name,
      "pluralName", WordHelper.plural(name),
      "description", processDomainDescription(context)
    );
  }

  /**
   * 加工领域名称
   * <p>
   * 将表名去除前缀, 格式从下划线转换为大驼峰
   *
   * @param table 数据库表
   * @return 领域名称
   */
  protected String processDomainName(DatabaseTable table) {
    String tableName = table.getName();
    return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.replace(tablePrefix, ""));
  }

  /**
   * 加工领域描述
   * <p>
   * 将表备注去除后缀
   *
   * @param table 数据库表
   * @return 领域描述
   */
  protected String processDomainDescription(DatabaseTable table) {
    String remarks = table.getRemarks();
    return remarks.endsWith(TableHelper.TABLE_REMARKS_SUFFIX) ? remarks.substring(0, remarks.length() - 1) : remarks;
  }
}
