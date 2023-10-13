package cn.koala.persist.support;

import cn.koala.persist.EntityListenerMethodAnnotationFactory;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * TODO: 修改类描述
 *
 * @author Houtaroy
 */
public class CrudServiceEntityListenerMethodAnnotationFactory implements EntityListenerMethodAnnotationFactory {

  private static final Map<String, Class<? extends Annotation>> pres = Map.of(
    "create", PrePersist.class,
    "update", PreUpdate.class,
    "delete", PreRemove.class
  );

  private static final Map<String, Class<? extends Annotation>> posts = Map.of(
    "create", PostPersist.class,
    "update", PostUpdate.class,
    "delete", PostRemove.class
  );

  @Override
  public Class<? extends Annotation> getEntityListenerMethodPreAnnotation(Method method) {
    return pres.get(method.getName());
  }

  @Override
  public Class<? extends Annotation> getEntityListenerMethodPostAnnotation(Method method) {
    return posts.get(method.getName());
  }
}
