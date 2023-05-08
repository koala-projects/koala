package cn.koala.persist.listener;

import cn.koala.toolkit.ReflectionHelper;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.experimental.SuperBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 实体监听器包装器
 *
 * @author Houtaroy
 */
@SuperBuilder(toBuilder = true)
public class EntityListenerWrapper {

  protected final EntityListener listener;
  protected final List<Method> prePersists;
  protected final List<Method> postPersists;
  protected final List<Method> preUpdates;
  protected final List<Method> postUpdates;
  protected final List<Method> preDeletes;
  protected final List<Method> postDeletes;

  public static EntityListenerWrapper from(EntityListener listener) {
    return EntityListenerWrapper.builder()
      .listener(listener)
      .prePersists(getMethods(listener, PrePersist.class))
      .postPersists(getMethods(listener, PostPersist.class))
      .preUpdates(getMethods(listener, PreUpdate.class))
      .postUpdates(getMethods(listener, PostUpdate.class))
      .preDeletes(getMethods(listener, PreRemove.class))
      .postDeletes(getMethods(listener, PostRemove.class))
      .build();
  }

  protected static List<Method> getMethods(EntityListener listener, Class<? extends Annotation> annotation) {
    Method[] methods = listener.getClass().getMethods();
    List<Method> result = new ArrayList<>(methods.length);
    for (Method method : methods) {
      if (method.isAnnotationPresent(annotation)) {
        result.add(method);
      }
    }
    return result;
  }

  public void prePersist(Object[] args) {
    listen(prePersists, args);
  }

  public void postPersist(Object[] args) {
    listen(postPersists, args);
  }

  public void preUpdate(Object[] args) {
    listen(preUpdates, args);
  }

  public void postUpdate(Object[] args) {
    listen(postUpdates, args);
  }

  public void preDelete(Object[] args) {
    listen(preDeletes, args);
  }

  public void postDelete(Object[] args) {
    listen(postDeletes, args);
  }

  protected void listen(List<Method> methods, Object[] args) {
    methods.stream()
      .filter(method -> ReflectionHelper.isMethodInvokable(method, args))
      .forEach(method -> ReflectionHelper.invokeMethod(method, listener, args));
  }
}
