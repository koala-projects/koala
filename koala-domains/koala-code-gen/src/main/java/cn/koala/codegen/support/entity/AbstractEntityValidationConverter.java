package cn.koala.codegen.support.entity;

import cn.koala.codegen.support.TableHelper;
import cn.koala.database.DatabaseTableColumn;

import java.util.List;

/**
 * 实体校验转换器抽象类
 * <p>
 * 根据Java数据类型判断是否支持转换
 *
 * @author Houtaroy
 */
public abstract class AbstractEntityValidationConverter implements EntityValidationConverter {

  protected final List<String> javaTypeNames;

  public AbstractEntityValidationConverter(List<String> javaTypeNames) {
    this.javaTypeNames = javaTypeNames;
  }

  @Override
  public boolean support(DatabaseTableColumn column) {
    return column.getSize() > 0 && javaTypeNames.contains(TableHelper.determinedJavaTypeName(column));
  }
}
