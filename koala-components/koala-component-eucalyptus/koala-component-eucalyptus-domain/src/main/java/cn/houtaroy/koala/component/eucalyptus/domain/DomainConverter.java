package cn.houtaroy.koala.component.eucalyptus.domain;

import cn.houtaroy.koala.component.jdbc.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Houtaroy
 */
public class DomainConverter {

  /**
   * 转换为领域定义
   *
   * @param table 数据库表
   * @return 领域定义
   */
  public Domain convert(Table table) {
    Domain result = new Domain();
    result.setName(table.getName());
    result.setDescription(table.getComment());
    List<Property> properties = new ArrayList<>(table.getColumns().size());
    table.getColumns().forEach(column -> {
      Property property = new Property();
      property.setName(column.getName());
      property.setType(column.getType());
      property.setDescription(column.getComment());
      properties.add(property);
    });
    result.setProperties(properties);
    return result;
  }
}
