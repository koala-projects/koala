package cn.koala.codegen.support.entity;

import cn.koala.codegen.support.domain.JavaType;
import cn.koala.database.DatabaseTableColumn;

import java.util.List;
import java.util.Map;

/**
 * 长度校验转换器
 *
 * @author Houtaroy
 */
public class SizeEntityValidationConverter extends AbstractEntityValidationConverter {

  public SizeEntityValidationConverter() {
    super(List.of(JavaType.STRING.getName()));
  }

  @Override
  public EntityValidation convert(DatabaseTableColumn column) {
    return EntityValidation.builder()
      .name("Size")
      .parameters(Map.of("max", column.getSize()))
      .message("%s长度不能大于%d".formatted(column.getRemarks(), column.getSize()))
      .groups(List.of("Create"))
      .build();
  }
}
