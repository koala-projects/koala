package cn.koala.persist;

import cn.koala.persist.listener.EntityListener;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础增删改查抽象类
 *
 * @author Houtaroy
 */
public abstract class BaseListenableCrudService<T, ID> extends BaseCrudService<T, ID> {
  protected final List<EntityListener> listeners;

  public BaseListenableCrudService(CrudRepository<T, ID> repository) {
    super(repository);
    this.listeners = new ArrayList<>();
  }

  public BaseListenableCrudService(CrudRepository<T, ID> repository, List<EntityListener> listeners) {
    super(repository);
    this.listeners = new ArrayList<>(listeners);
  }

  @Override
  public <S extends T> void add(@NonNull S entity) {
    listeners.forEach(listener -> listener.beforeAdd(entity));
    super.add(entity);
    listeners.forEach(listener -> listener.afterAdd(entity));
  }

  @Override
  public <S extends T> void update(@NonNull S entity) {
    listeners.forEach(listener -> listener.beforeUpdate(entity));
    super.update(entity);
    listeners.forEach(listener -> listener.afterUpdate(entity));
  }

  @Override
  public <S extends T> void delete(@NonNull S entity) {
    listeners.forEach(listener -> listener.beforeDelete(entity));
    super.delete(entity);
    listeners.forEach(listener -> listener.afterDelete(entity));
  }
}
