package cn.koala.persist.support;

import cn.koala.persist.SystemEntityListener;
import cn.koala.util.ClassUtils;

/**
 * 系统实体监听器抽象类
 * <p>
 * 监听类及其子类的所有实体
 *
 * @author Houtaroy
 */
public abstract class AbstractSystemEntityListener<T> implements SystemEntityListener {

  protected final Class<T> supportEntityClass;

  @SuppressWarnings("unchecked")
  public AbstractSystemEntityListener() {
    supportEntityClass = (Class<T>) ClassUtils.getGenericClass(getClass(), AbstractSystemEntityListener.class);
  }

  @Override
  public boolean support(Class<?> entityClass) {
    return supportEntityClass.isAssignableFrom(entityClass);
  }
}
