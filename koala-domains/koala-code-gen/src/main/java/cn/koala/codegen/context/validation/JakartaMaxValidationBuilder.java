package cn.koala.codegen.context.validation;

import cn.koala.database.domain.DatabaseTableColumn;

import java.util.List;

/**
 * 最大值校验构建器
 *
 * @author Houtaroy
 */
public class JakartaMaxValidationBuilder implements JakartaValidationBuilder {

  private static final String TEMPLATE = "@Max(value = %d, message = \"%s最大值不能大于%d\", groups = {Create.class})";

  private static final List<Integer> SUPPORT_COLUMN_TYPES = List.of(-6, -5, 4, 5);

  @Override
  public String build(DatabaseTableColumn column) {
    long maxValue = obtainMaxValue(column);
    return TEMPLATE.formatted(maxValue, column.getRemarks(), maxValue);
  }

  private long obtainMaxValue(DatabaseTableColumn column) {
    return (long) Math.pow(10, Math.min(column.getSize(), 9)) - 1;
  }

  @Override
  public boolean support(DatabaseTableColumn column) {
    return SUPPORT_COLUMN_TYPES.contains(column.getType());
  }
}
