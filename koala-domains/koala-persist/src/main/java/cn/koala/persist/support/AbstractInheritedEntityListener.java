package cn.koala.persist.support;

/**
 * TODO: 修改类描述
 *
 * @author Houtaroy
 */
public abstract class AbstractInheritedEntityListener<T> extends AbstractEntityListener<T> {

  @Override
  public boolean support(Object entity) {
    return entityClass.isAssignableFrom(entity.getClass());
  }
}
