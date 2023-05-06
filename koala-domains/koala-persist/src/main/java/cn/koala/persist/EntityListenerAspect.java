package cn.koala.persist;

import cn.koala.persist.support.CreateEntityListenerStrategy;
import cn.koala.persist.support.DeleteEntityListenerStrategy;
import cn.koala.persist.support.UpdateEntityListenerStrategy;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * 实体监听器切面
 *
 * @author Houtaroy
 */
@Aspect
public class EntityListenerAspect {

  private static final Map<CrudType, EntityListenerStrategy> DEFAULT_STRATEGIES = Map.of(
    CrudType.CREATE, new CreateEntityListenerStrategy(),
    CrudType.UPDATE, new UpdateEntityListenerStrategy(),
    CrudType.DELETE, new DeleteEntityListenerStrategy()
  );

  private final EntityListenerManager manager;
  private final Map<CrudType, EntityListenerStrategy> strategies;

  public EntityListenerAspect(EntityListenerManager manager) {
    this(manager, DEFAULT_STRATEGIES);
  }

  public EntityListenerAspect(EntityListenerManager manager, Map<CrudType, EntityListenerStrategy> strategies) {
    this.manager = manager;
    this.strategies = strategies;
  }

  @Around("@annotation(action)")
  public Object around(ProceedingJoinPoint joinPoint, EntityListenAction action) throws Throwable {
    Object[] args = joinPoint.getArgs();
    CrudType type = determineCrudType(action);
    Class<?> entityClass = determineEntityClass(action, args);
    EntityListenerStrategy strategy = strategies.get(type);
    List<EntityListenerWrapper> listeners = manager.getListeners(entityClass);
    if (isSkippable(entityClass, listeners, strategy)) {
      return joinPoint.proceed();
    }
    if (action.pre()) {
      listeners.forEach(listener -> strategy.pre(listener, args));
    }
    Object result = joinPoint.proceed();
    if (action.post()) {
      listeners.forEach(listener -> strategy.post(listener, args));
    }
    return result;
  }

  protected CrudType determineCrudType(EntityListenAction action) {
    return action.value() == CrudType.UNDEFINED ? action.type() : action.value();
  }

  protected Class<?> determineEntityClass(EntityListenAction action, Object[] args) {
    if (action.entity() != null && action.entity() != Void.class) {
      return action.entity();
    }
    if (ObjectUtils.isEmpty(args)) {
      return null;
    }
    return args[0].getClass();
  }

  protected boolean isSkippable(Class<?> entityClass, List<EntityListenerWrapper> listeners,
                                EntityListenerStrategy strategy) {
    return entityClass == null || listeners.isEmpty() || strategy == null;
  }
}
