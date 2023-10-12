package cn.koala.codegen.support.domain;

import cn.koala.database.DatabaseTableColumn;

/**
 * 领域属性类型转换器
 *
 * @author Houtaroy
 */
@FunctionalInterface
public interface DomainPropertyTypeConverter {

  String convert(DatabaseTableColumn column);
}
