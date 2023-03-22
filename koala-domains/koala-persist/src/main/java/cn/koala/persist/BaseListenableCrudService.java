package cn.koala.persist;

import cn.koala.persist.domain.Persistable;
import cn.koala.persist.listener.EntityListener;
import cn.koala.persist.listener.EntityListenerSupport;
import lombok.NonNull;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 基础增删改查抽象类
 *
 * @author Houtaroy
 */
public abstract class BaseListenableCrudService<T, ID> extends BaseCrudService<T, ID> implements EntityListenerSupport {
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
    listeners.forEach(listener -> listener.beforeUpdate(entity, loadPersist(entity)));
    super.update(entity);
    listeners.forEach(listener -> listener.afterUpdate(entity));
  }

  @Override
  public <S extends T> void delete(@NonNull S entity) {
    listeners.forEach(listener -> listener.beforeDelete(entity, loadPersist(entity)));
    super.delete(entity);
    listeners.forEach(listener -> listener.afterDelete(entity));
  }

  @Override
  public void registerListener(EntityListener listener) {
    this.listeners.add(listener);
  }

  @Override
  public void registerListeners(List<EntityListener> listeners) {
    this.listeners.clear();
    this.listeners.addAll(listeners);
  }

  @Override
  public Optional<Class<?>> getEntityType() {
    return Optional.of(getClass().getGenericSuperclass())
      .filter(superClass -> superClass instanceof ParameterizedType)
      .map(ParameterizedType.class::cast)
      .map(ParameterizedType::getActualTypeArguments)
      .filter(types -> types.length > 0)
      .map(types -> types[0])
      .filter(entityType -> entityType instanceof Class<?>)
      .map(entityType -> (Class<?>) entityType);
  }

  protected <S extends T> T loadPersist(S entity) {
    return Optional.of(entity).filter(data -> data instanceof Persistable<?>)
      .map(data -> load(((Persistable<ID>) data).getId()))
      .orElse(entity);
  }
}
