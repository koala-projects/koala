package cn.koala.eucalyptus;

import cn.koala.utils.JdbcColumn;
import cn.koala.utils.JdbcTable;
import com.google.common.base.CaseFormat;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class JdbcDomainFactory implements DomainFactory {
  private final String tablePrefix;

  @Override
  public SimpleDomain create(Object parameter) {
    if (parameter instanceof JdbcTable table) {
      return create(table);
    }
    return new SimpleDomain();
  }

  /**
   * 创建领域模型
   *
   * @param table 数据库表
   * @return 领域模型
   */
  protected SimpleDomain create(JdbcTable table) {
    SimpleDomain result = new SimpleDomain();
    result.setCode(tableCode2DomainCode(table.getCode()));
    result.setName(tableName2DomainName(table.getName()));
    List<JdbcColumn> columns = table.getColumns();
    List<SimpleDomainProperty> properties = new ArrayList<>(columns.size());
    columns.forEach(column -> {
      SimpleDomainProperty property = column2DomainProperty(column);
      Consumer<SimpleDomainProperty> consumer = column.isId() ? result::setIdProperty : properties::add;
      consumer.accept(property);
    });
    result.setProperties(properties);
    return result;
  }

  /**
   * 数据库列转换为领域属性
   *
   * @param column 数据库列
   * @return 领域属性
   */
  protected SimpleDomainProperty column2DomainProperty(JdbcColumn column) {
    return new SimpleDomainProperty(column.getName(), column.getComment(), column.getType());
  }

  /**
   * 数据库表代码转换为领域代码
   *
   * @param tableCode 数据库表代码
   * @return 领域代码
   */
  protected String tableCode2DomainCode(String tableCode) {
    return CaseFormat.UPPER_UNDERSCORE.to(
      CaseFormat.UPPER_CAMEL,
      tableCode.substring(tablePrefix.length()).toLowerCase()
    );
  }

  /**
   * 数据库表名称转换为领域名称
   *
   * @param tableName 数据库表名称
   * @return 领域名称
   */
  protected String tableName2DomainName(String tableName) {
    return tableName.endsWith("表") ? tableName.substring(0, tableName.length() - 1) : tableName;
  }
}
