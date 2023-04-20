package cn.koala.code.processors.java;

import cn.koala.code.processors.AbstractContextProcessor;
import cn.koala.database.DatabaseTable;
import cn.koala.toolkit.word.WordHelper;
import com.google.common.base.CaseFormat;

import java.util.Map;

/**
 * TODO: 修改类描述
 *
 * @author Houtaroy
 */
public class DomainNameProcessor extends AbstractContextProcessor<DatabaseTable> {

  private final String tablePrefix;

  public DomainNameProcessor() {
    this(KoalaHelper.TABLE_PREFIX);
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

  protected String processDomainName(DatabaseTable table) {
    String tableName = table.getName();
    return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.replace(tablePrefix, ""));
  }

  protected String processDomainDescription(DatabaseTable table) {
    String remarks = table.getRemarks();
    return remarks.endsWith("表") ? remarks.substring(0, remarks.length() - 1) : remarks;
  }
}
