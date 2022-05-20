package cn.koala.eucalyptus;

import cn.koala.jdbc.Table;
import com.google.common.base.CaseFormat;
import lombok.AllArgsConstructor;

import java.util.Optional;

/**
 * @author Houtaroy
 */
@AllArgsConstructor
public class Converter {
  protected ConvertProperties properties;

  /**
   * 构造函数
   */
  public Converter() {
    properties = new ConvertProperties();
  }

  /**
   * 转换为领域定义
   *
   * @param table 数据库表
   * @return 领域定义
   */
  public Domain convert(Table table, Domain domain) {
    domain.setName(tableName2DomainName(table.getName()));
    domain.setDescription(tableComment2DomainDescription(table.getComment()));
    table.getColumns().forEach(column -> {
      Property property = new Property();
      property.setName(columnName2PropertyName(column.getName()));
      property.setType(columnType2PropertyType(column.getType()));
      property.setDescription(column.getComment());
      domain.addProperties(property);
    });
    return domain;
  }

  protected String columnName2PropertyName(String columnName) {
    return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName.toLowerCase());
  }

  protected String columnType2PropertyType(String columnType) {
    return Optional.ofNullable(properties.getTypeMap().get(columnType)).orElse("String");
  }

  protected String tableComment2DomainDescription(String tableComment) {
    return tableComment.endsWith("表") ? tableComment.substring(0, tableComment.length() - 1) : tableComment;
  }

  protected String tableName2DomainName(String tableName) {
    return CaseFormat.UPPER_UNDERSCORE.to(
      CaseFormat.UPPER_CAMEL,
      tableName.substring(properties.getPrefix().length()).toLowerCase()
    );
  }
}
