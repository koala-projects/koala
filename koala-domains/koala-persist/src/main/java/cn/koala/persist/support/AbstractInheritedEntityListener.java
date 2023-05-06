package cn.koala.persist.support;

/**
 * 支持继承的实体监听器抽象类
 * <p>
 * 同时监听类及其子类的实体
 *
 * @author Houtaroy
 */
public abstract class AbstractInheritedEntityListener<T> extends AbstractEntityListener<T> {

  @Override
  public boolean support(Class<?> entityClass) {
    return this.entityClass.isAssignableFrom(entityClass);
  }
}
