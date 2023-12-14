package cn.koala.codegen.context.validation;


import cn.koala.database.domain.DatabaseTableColumn;

/**
 * 校验构建器
 *
 * @author Houtaroy
 */
public interface JakartaValidationBuilder {

  String build(DatabaseTableColumn column);

  boolean support(DatabaseTableColumn column);
}
