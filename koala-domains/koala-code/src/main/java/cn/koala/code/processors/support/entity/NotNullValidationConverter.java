package cn.koala.code.processors.support.entity;

import cn.koala.code.processors.support.TableHelper;
import cn.koala.database.DatabaseTableColumn;

import java.util.List;
import java.util.Map;

/**
 * 不为空校验转换器
 *
 * @author Houtaroy
 */
public class NotNullValidationConverter implements EntityValidationConverter {

  @Override
  public EntityValidation convert(DatabaseTableColumn column) {
    return EntityValidation.builder()
      .name(determineName(column.getType()))
      .parameters(Map.of())
      .message("%s不允许为空".formatted(column.getRemarks()))
      .groups(List.of("Create"))
      .build();
  }

  protected String determineName(Integer columnType) {
    return JavaType.String.getName().equals(TableHelper.columnType2JavaType(columnType)) ? "NotBlank" : "NotNull";
  }

  @Override
  public boolean support(DatabaseTableColumn column) {
    return !column.getIsNullable();
  }
}
