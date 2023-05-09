package cn.koala.code.processors.support.entity;

import cn.koala.database.DatabaseTableColumn;

import java.util.List;
import java.util.Map;

/**
 * TODO: 修改类描述
 *
 * @author Houtaroy
 */
public class SizeValidationConverter extends AbstractSizeValidationConverter {

  public SizeValidationConverter() {
    super(List.of(JavaType.String.getName()));
  }

  @Override
  public EntityValidation convert(DatabaseTableColumn column) {
    return EntityValidation.builder()
      .name("Size")
      .parameters(Map.of("max", column.getSize()))
      .message("%s长度不能大于%d".formatted(column.getRemarks(), column.getSize()))
      .groups(List.of("Add"))
      .build();
  }
}
