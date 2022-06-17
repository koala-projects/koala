package cn.koala.eucalyptus;

import cn.koala.utils.JdbcTable;
import cn.koala.utils.WordUtil;
import com.google.common.base.CaseFormat;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class JdbcDomainFactory implements DomainFactory {
  private final String tablePrefix;

  @Override
  public JdbcDomain create(Object data) {
    if (data instanceof JdbcTable table) {
      return create(table);
    }
    return new JdbcDomain();
  }

  /**
   * 创建领域模型
   *
   * @param table 数据库表
   * @return 领域模型
   */
  protected JdbcDomain create(JdbcTable table) {
    JdbcDomain result = new JdbcDomain();
    result.setTableName(table.getName());
    result.setCode(tableName2DomainCode(table.getName()));
    result.setClassName(StringUtils.capitalize(result.getCode()));
    result.setPluralCode(WordUtil.plural(result.getCode()));
    result.setName(tableComment2DomainName(table.getName()));
    List<JdbcDomainProperty> properties = new ArrayList<>(table.getColumns().size());
    table.getColumns().forEach(column -> {
      JdbcDomainProperty property = new JdbcDomainProperty(column);
      properties.add(property);
      if (property.isId()) {
        result.setIdProperty(property);
      }
    });
    result.setProperties(properties);
    return result;
  }

  /**
   * 数据库表代码转换为领域代码
   *
   * @param tableCode 数据库表代码
   * @return 领域代码
   */
  protected String tableName2DomainCode(String tableCode) {
    return CaseFormat.LOWER_UNDERSCORE.to(
      CaseFormat.LOWER_CAMEL,
      tableCode.substring(tablePrefix.length()).toLowerCase()
    );
  }

  /**
   * 数据库表名称转换为领域名称
   *
   * @param tableName 数据库表名称
   * @return 领域名称
   */
  protected String tableComment2DomainName(String tableName) {
    return tableName.endsWith("表") ? tableName.substring(0, tableName.length() - 1) : tableName;
  }
}
