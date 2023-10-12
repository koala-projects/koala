package cn.koala.codegen.support.domain;

import cn.koala.database.DatabaseTableColumn;
import lombok.Getter;

/**
 * TODO: 修改类描述
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
