package cn.koala.persist;

import cn.koala.persist.domain.Persistable;
import cn.koala.persist.listener.EntityListener;
import cn.koala.persist.listener.EntityListenerSupport;
import cn.koala.toolkit.ClassHelper;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础增删改查抽象类
 *
 * @author Houtaroy
 */
public abstract class BaseListenableCrudService<T, ID> extends BaseCrudService<T, ID> implements EntityListenerSupport<T> {
  protected final Class<T> entityClass;
  protected final List<EntityListener<? super T>> listeners;

  public BaseListenableCrudService(CrudRepository<T, ID> repository) {
    this(repository, new ArrayList<>());
  }

  @SuppressWarnings("unchecked")
  public BaseListenableCrudService(CrudRepository<T, ID> repository, List<EntityListener<? super T>> listeners) {
    super(repository);
    this.entityClass = (Class<T>) ClassHelper.getSuperGenericClass(this, BaseCrudService.class);
    this.listeners = new ArrayList<>(listeners);
  }

  @Override
  public <S extends T> void add(@NonNull S entity) {
    listeners.forEach(listener -> listener.preAdd(entity));
    super.add(entity);
    listeners.forEach(listener -> listener.postAdd(entity));
  }

  @Override
  public <S extends T> void update(@NonNull S entity) {
    listeners.forEach(listener -> listener.preUpdate(entity, loadPersist(entity)));
    super.update(entity);
    listeners.forEach(listener -> listener.postUpdate(entity));
  }

  @Override
  public <S extends T> void delete(@NonNull S entity) {
    listeners.forEach(listener -> listener.preDelete(entity, loadPersist(entity)));
    super.delete(entity);
    listeners.forEach(listener -> listener.postDelete(entity));
  }

  @Override
  public Class<T> getEntityClass() {
    return this.entityClass;
  }

  @Override
  public void registerListener(EntityListener<? super T> listener) {
    this.listeners.add(listener);
  }

  @Override
  public void registerListeners(List<EntityListener<? super T>> listeners) {
    this.listeners.clear();
    this.listeners.addAll(listeners);
  }

  @SuppressWarnings("unchecked")
  protected T loadPersist(T entity) {
    return entity instanceof Persistable<?> ? load(((Persistable<ID>) entity).getId()) : entity;
  }
}
