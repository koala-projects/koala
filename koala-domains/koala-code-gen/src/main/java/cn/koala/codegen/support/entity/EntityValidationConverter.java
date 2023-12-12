package cn.koala.codegen.support.entity;


import cn.koala.database.domain.DatabaseTableColumn;

/**
 * 实体校验转换器
 *
 * @author Houtaroy
 */
public interface EntityValidationConverter {

  EntityValidation convert(DatabaseTableColumn column);

  boolean support(DatabaseTableColumn column);
}
