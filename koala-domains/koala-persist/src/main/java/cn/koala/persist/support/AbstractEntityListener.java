package cn.koala.persist.support;

import cn.koala.persist.EntityListener;
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
    this.entityClass = (Class<T>) ClassHelper.getSuperGenericClass(this, AbstractEntityListener.class);
  }

  @Override
  public boolean support(Object entity) {
    return Objects.equals(entityClass, entity.getClass());
  }
}
