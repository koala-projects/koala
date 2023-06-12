package cn.koala.code.processors.support.entity;

import cn.koala.database.DatabaseTableColumn;

import java.util.List;
import java.util.Map;

/**
 * 小数校验转换器
 *
 * @author Houtaroy
 */
public class DigitsValidationConverter extends AbstractSizeValidationConverter {

  public DigitsValidationConverter() {
    super(List.of(JavaType.Float.getName(), JavaType.Double.getName()));
  }

  @Override
  public EntityValidation convert(DatabaseTableColumn column) {
    return EntityValidation.builder()
      .name("Digits")
      .parameters(Map.of("integer", column.getSize(), "fraction", column.getDecimalDigits()))
      .message(
        "%s必须为小数，整数为%d位，小数为%d位".formatted(column.getRemarks(), column.getSize(), column.getDecimalDigits())
      )
      .groups(List.of("Create"))
      .build();
  }
}
