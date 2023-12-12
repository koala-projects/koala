package cn.koala.codegen.support.domain;

import cn.koala.database.domain.DatabaseTableColumn;
import lombok.Getter;

/**
 * 命名的领域属性类型转换器
 *
 * @author Houtaroy
 */
@Getter
public class NamedDomainPropertyTypeConverter implements DomainPropertyTypeConverter {

  private final String name;
  private final DomainPropertyTypeConverter converter;

  public NamedDomainPropertyTypeConverter(String name, DomainPropertyTypeConverter converter) {
    this.name = name;
    this.converter = converter;
  }

  @Override
  public String convert(DatabaseTableColumn column) {
    return this.converter.convert(column);
  }
}
