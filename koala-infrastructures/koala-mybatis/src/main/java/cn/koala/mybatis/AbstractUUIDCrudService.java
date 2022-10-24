package cn.koala.mybatis;

import cn.koala.persistence.Idable;

import java.util.UUID;

/**
 * ID为UUID的增删改查抽象类
 *
 * @param <T> 数据实体类型
 * @author Houtaroy
 */
public abstract class AbstractUUIDCrudService<T> extends AbstractCrudService<String, T> {
  @Override
  public void add(T entity) {
    if (entity instanceof Idable<?>) {
      ((Idable<String>) entity).setIdIfAbsent(UUID.randomUUID().toString());
    }
    super.add(entity);
  }
}
