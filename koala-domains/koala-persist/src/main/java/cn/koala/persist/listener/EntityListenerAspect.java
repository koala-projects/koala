package cn.koala.persist.listener;

import cn.koala.persist.CrudType;
import cn.koala.persist.listener.support.CreateEntityListenerStrategy;
import cn.koala.persist.listener.support.DeleteEntityListenerStrategy;
import cn.koala.persist.listener.support.UpdateEntityListenerStrategy;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * 实体监听器切面
 *
 * @author Houtaroy
 */
@Aspect
@Order(3200)
public class EntityListenerAspect {

  private static final Map<CrudType, EntityListenerStrategy> DEFAULT_STRATEGIES = Map.of(
    CrudType.CREATE, new CreateEntityListenerStrategy(),
    CrudType.UPDATE, new UpdateEntityListenerStrategy(),
    CrudType.DELETE, new DeleteEntityListenerStrategy()
  );

  private final List<EntityListenerWrapper> wrappers;

  private final Map<CrudType, EntityListenerStrategy> strategies = DEFAULT_STRATEGIES;

  public EntityListenerAspect(List<EntityListener> listeners) {
    this.wrappers = listeners.stream().map(EntityListenerWrapper::from).toList();
  }

  @Around("@annotation(action)")
  public Object around(ProceedingJoinPoint joinPoint, EntityListenAction action) throws Throwable {
    Object[] args = joinPoint.getArgs();
    CrudType type = action.value();
    Class<?> entityClass = determineEntityClass(action, args);
    EntityListenerStrategy strategy = strategies.get(type);
    List<EntityListenerWrapper> wrappers = this.getWrappers(entityClass);
    if (isSkippable(entityClass, wrappers, strategy)) {
      return joinPoint.proceed();
    }
    if (action.pre()) {
      wrappers.forEach(wrapper -> strategy.pre(wrapper, args));
    }
    Object result = joinPoint.proceed();
    if (action.post()) {
      wrappers.forEach(wrapper -> strategy.post(wrapper, args));
    }
    return result;
  }

  protected Class<?> determineEntityClass(EntityListenAction action, Object[] args) {
    if (action.entity() != Void.class) {
      return action.entity();
    }
    if (ObjectUtils.isEmpty(args)) {
      return null;
    }
    return args[0].getClass();
  }

  protected List<EntityListenerWrapper> getWrappers(Class<?> entityClass) {
    return this.wrappers.stream().filter(wrapper -> wrapper.getListener().support(entityClass)).toList();
  }

  protected boolean isSkippable(Class<?> entityClass, List<EntityListenerWrapper> wrappers,
                                EntityListenerStrategy strategy) {
    return entityClass == null || wrappers.isEmpty() || strategy == null;
  }
}
