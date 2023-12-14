package cn.koala.codegen.context.validation;

import cn.koala.database.domain.DatabaseTableColumn;

import java.util.List;

/**
 * 小数校验转换器
 *
 * @author Houtaroy
 */
public class JakartaDigitsValidationBuilder implements JakartaValidationBuilder {

  private static final String TEMPLATE =
    "@Digits(integer = %d, fraction = %d, message = \"%s必须为小数，整数为%d位，小数为%d位\", groups = {Create.class})";

  private static final List<Integer> SUPPORT_COLUMN_TYPES = List.of(2, 3, 6, 8);

  public String build(DatabaseTableColumn column) {
    return TEMPLATE.formatted(
      column.getSize(),
      column.getDecimalDigits(),
      column.getRemarks(),
      column.getSize(),
      column.getDecimalDigits()
    );
  }

  @Override
  public boolean support(DatabaseTableColumn column) {
    return SUPPORT_COLUMN_TYPES.contains(column.getType());
  }
}
