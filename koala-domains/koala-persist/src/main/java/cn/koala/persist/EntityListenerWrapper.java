package cn.koala.persist;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 实体监听器包装器
 *
 * @author Houtaroy
 */
@SuperBuilder(toBuilder = true)
public class EntityListenerWrapper {

  protected final EntityListener listener;
  protected final Method prePersist;
  protected final Method postPersist;
  protected final Method preUpdate;
  protected final Method postUpdate;
  protected final Method preDelete;
  protected final Method postDelete;

  public static EntityListenerWrapper from(EntityListener listener) {
    return EntityListenerWrapper.builder()
      .listener(listener)
      .prePersist(getMethod(listener, PrePersist.class))
      .postPersist(getMethod(listener, PostPersist.class))
      .preUpdate(getMethod(listener, PreUpdate.class))
      .postUpdate(getMethod(listener, PostUpdate.class))
      .preDelete(getMethod(listener, PreRemove.class))
      .postDelete(getMethod(listener, PostRemove.class))
      .build();
  }

  @Nullable
  protected static Method getMethod(EntityListener listener, Class<? extends Annotation> annotation) {
    Method[] methods = listener.getClass().getMethods();
    for (Method method : methods) {
      if (method.isAnnotationPresent(annotation)) {
        return method;
      }
    }
    return null;
  }

  public void prePersist(Object[] args) {
    listen(prePersist, args);
  }

  public void postPersist(Object[] args) {
    listen(postPersist, args);
  }

  public void preUpdate(Object[] args) {
    listen(preUpdate, args);
  }

  public void postUpdate(Object[] args) {
    listen(postUpdate, args);
  }

  public void preDelete(Object[] args) {
    listen(preDelete, args);
  }

  public void postDelete(Object[] args) {
    listen(postDelete, args);
  }

  protected void listen(Method method, Object[] args) {
    if (method != null) {
      ReflectionUtils.invokeMethod(method, listener, args);
    }
  }
}
