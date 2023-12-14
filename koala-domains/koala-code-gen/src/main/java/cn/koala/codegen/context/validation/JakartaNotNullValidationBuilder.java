package cn.koala.codegen.context.validation;

import cn.koala.database.domain.DatabaseTableColumn;

/**
 * 不为空校验构建器
 *
 * @author Houtaroy
 */
public class JakartaNotNullValidationBuilder implements JakartaValidationBuilder {

  private static final String TEMPLATE = "@NotNull(message = \"%s不允许为空\", groups = {Create.class})";

  @Override
  public String build(DatabaseTableColumn column) {
    return TEMPLATE.formatted(column.getRemarks());
  }

  @Override
  public boolean support(DatabaseTableColumn column) {
    return !column.getIsNullable();
  }
}
