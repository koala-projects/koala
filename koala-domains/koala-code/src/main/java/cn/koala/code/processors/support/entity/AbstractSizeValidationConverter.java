package cn.koala.code.processors.support.entity;

import cn.koala.code.processors.support.TableHelper;
import cn.koala.database.DatabaseTableColumn;

import java.util.List;

/**
 * 长度校验抽象类
 *
 * @author Houtaroy
 */
public abstract class AbstractSizeValidationConverter implements EntityValidationConverter {

  protected final List<String> javaTypes;

  public AbstractSizeValidationConverter(List<String> javaTypes) {
    this.javaTypes = javaTypes;
  }

  @Override
  public boolean support(DatabaseTableColumn column) {
    return column.getSize() > 0 && javaTypes.contains(TableHelper.columnType2JavaType(column.getType()));
  }
}
