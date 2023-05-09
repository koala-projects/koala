package cn.koala.code.processors.support.entity;

import cn.koala.database.DatabaseTableColumn;

import java.util.List;
import java.util.Map;

/**
 * 最大值校验转换器
 *
 * @author Houtaroy
 */
public class MaxValidationConverter extends AbstractSizeValidationConverter {

  public MaxValidationConverter() {
    super(List.of(JavaType.Integer.getName(), JavaType.Long.getName()));
  }

  @Override
  public EntityValidation convert(DatabaseTableColumn column) {
    Long value = computeMaxValue(column);
    return EntityValidation.builder()
      .name("Max")
      .parameters(Map.of("value", value))
      .message("%s最大值不能大于%d".formatted(column.getRemarks(), value))
      .groups(List.of("Add"))
      .build();
  }

  protected Long computeMaxValue(DatabaseTableColumn column) {
    return (long) Math.pow(10, Math.min(column.getSize(), 9)) - 1;
  }
}
