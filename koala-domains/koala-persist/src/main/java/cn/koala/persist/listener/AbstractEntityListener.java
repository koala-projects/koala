package cn.koala.persist.listener;

import cn.koala.toolkit.ClassHelper;
import lombok.Data;

/**
 * 抽象实体监听器
 * <p>
 * 实现了自动获取实体类型功能
 *
 * @author Houtaroy
 */
@Deprecated
@Data
public abstract class AbstractEntityListener<T> implements EntityListener<T> {
  protected final Class<T> entityClass;

  @SuppressWarnings("unchecked")
  public AbstractEntityListener() {
    this.entityClass = (Class<T>) ClassHelper.getSuperGenericClass(this, AbstractEntityListener.class);
  }
}
