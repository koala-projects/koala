package cn.koala.code.processors.support.entity;

import cn.koala.database.DatabaseTableColumn;

/**
 * 校验转换器
 *
 * @author Houtaroy
 */
public interface EntityValidationConverter {

  EntityValidation convert(DatabaseTableColumn column);

  boolean support(DatabaseTableColumn column);
}
