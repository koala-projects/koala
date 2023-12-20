package cn.koala.mybatis.listener;

import cn.koala.util.ClassUtils;

/**
 * 全局实体监听器抽象类
 * <p>
 * 监听类及其子类的所有实体
 *
 * @author Houtaroy
 */
public abstract class AbstractGlobalEntityListener<T> implements GlobalEntityListener {

  protected final Class<T> supportEntityClass;

  @SuppressWarnings("unchecked")
  public AbstractGlobalEntityListener() {
    supportEntityClass = (Class<T>) ClassUtils.getGenericClass(getClass(), AbstractGlobalEntityListener.class);
  }

  @Override
  public boolean support(Class<?> entityClass) {
    return supportEntityClass.isAssignableFrom(entityClass);
  }
}
