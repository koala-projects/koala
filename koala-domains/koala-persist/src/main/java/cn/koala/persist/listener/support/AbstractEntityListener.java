package cn.koala.persist.listener.support;

import cn.koala.persist.listener.EntityListener;
import cn.koala.toolkit.ClassHelper;

import java.util.Objects;

/**
 * 实体监听器抽象类
 * <p>
 * 仅监听指定类型的实体
 *
 * @author Houtaroy
 */
public abstract class AbstractEntityListener<T> implements EntityListener {

  protected final Class<T> entityClass;

  @SuppressWarnings("unchecked")
  public AbstractEntityListener() {
    this.entityClass = (Class<T>) ClassHelper.getGenericClass(getClass(), AbstractEntityListener.class);
  }

  @Override
  public boolean support(Class<?> entityClass) {
    return Objects.equals(this.entityClass, entityClass);
  }
}
