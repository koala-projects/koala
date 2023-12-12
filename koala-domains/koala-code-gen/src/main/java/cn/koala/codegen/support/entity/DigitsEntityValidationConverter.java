package cn.koala.codegen.support.entity;

import cn.koala.codegen.support.domain.JavaType;
import cn.koala.database.domain.DatabaseTableColumn;

import java.util.List;
import java.util.Map;

/**
 * 小数校验转换器
 *
 * @author Houtaroy
 */
public class DigitsEntityValidationConverter extends AbstractEntityValidationConverter {

  public DigitsEntityValidationConverter() {
    super(List.of(JavaType.FLOAT.getName(), JavaType.DOUBLE.getName()));
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
