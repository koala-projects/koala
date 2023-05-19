package cn.koala.persist.listener;

import cn.koala.toolkit.ClassHelper;
import cn.koala.toolkit.ReflectionHelper;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 实体监听器包装器
 * <p>
 * 解析自定义实体监听器中的方法, 实际执行监听逻辑的包装类
 *
 * @author Houtaroy
 */
@SuperBuilder(toBuilder = true)
public class EntityListenerWrapper {

  @Getter
  protected final EntityListener listener;
  protected final List<Method> prePersists;
  protected final List<Method> postPersists;
  protected final List<Method> preUpdates;
  protected final List<Method> postUpdates;
  protected final List<Method> preDeletes;
  protected final List<Method> postDeletes;

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

  public static EntityListenerWrapper from(EntityListener listener) {
    Class<?> listenerClass = listener.getClass();
    return EntityListenerWrapper.builder()
      .listener(listener)
      .prePersists(ClassHelper.getMethods(listenerClass, PrePersist.class))
      .postPersists(ClassHelper.getMethods(listenerClass, PostPersist.class))
      .preUpdates(ClassHelper.getMethods(listenerClass, PreUpdate.class))
      .postUpdates(ClassHelper.getMethods(listenerClass, PostUpdate.class))
      .preDeletes(ClassHelper.getMethods(listenerClass, PreRemove.class))
      .postDeletes(ClassHelper.getMethods(listenerClass, PostRemove.class))
      .build();
  }
}
