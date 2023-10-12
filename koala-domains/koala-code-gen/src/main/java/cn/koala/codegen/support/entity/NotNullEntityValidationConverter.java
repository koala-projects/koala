package cn.koala.codegen.support.entity;

import cn.koala.codegen.support.TableHelper;
import cn.koala.codegen.support.domain.JavaType;
import cn.koala.database.DatabaseTableColumn;

import java.util.List;
import java.util.Map;

/**
 * 不为空校验转换器
 *
 * @author Houtaroy
 */
public class NotNullEntityValidationConverter implements EntityValidationConverter {

  @Override
  public EntityValidation convert(DatabaseTableColumn column) {
    return EntityValidation.builder()
      .name(determineName(column))
      .parameters(Map.of())
      .message("%s不允许为空".formatted(column.getRemarks()))
      .groups(List.of("Create"))
      .build();
  }

  protected String determineName(DatabaseTableColumn column) {
    return JavaType.STRING.getName().equals(TableHelper.determinedJavaTypeName(column)) ? "NotBlank" : "NotNull";
  }

  @Override
  public boolean support(DatabaseTableColumn column) {
    return !column.getIsNullable();
  }
}
