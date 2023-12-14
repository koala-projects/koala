package cn.koala.codegen.context.validation;

import cn.koala.database.domain.DatabaseTableColumn;

import java.util.List;

/**
 * 长度校验构建器
 *
 * @author Houtaroy
 */
public class JakartaSizeValidationBuilder implements JakartaValidationBuilder {

  private static final String TEMPLATE = "@Size(max = %d, message = \"%s长度不能大于%d\", groups = {Create.class})";

  private static final List<Integer> SUPPORT_COLUMN_TYPES = List.of(-16, -15, -9, -1, 1, 12, 1111);


  @Override
  public String build(DatabaseTableColumn column) {
    return TEMPLATE.formatted(column.getSize(), column.getRemarks(), column.getSize());
  }

  @Override
  public boolean support(DatabaseTableColumn column) {
    return SUPPORT_COLUMN_TYPES.contains(column.getType());
  }
}
