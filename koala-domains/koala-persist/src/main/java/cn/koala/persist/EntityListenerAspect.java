package cn.koala.persist;

import cn.koala.persist.support.CreateEntityListenerStrategy;
import cn.koala.persist.support.DeleteEntityListenerStrategy;
import cn.koala.persist.support.UpdateEntityListenerStrategy;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

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
  public Object around(ProceedingJoinPoint joinPoint, CrudAction action) throws Throwable {
    Object[] args = joinPoint.getArgs();
    Object entity = args[action.entity()];
    List<EntityListenerWrapper> listeners = manager.getListeners(entity);
    EntityListenerStrategy strategy = strategies.get(action.value());
    if (isSkippable(entity, listeners, strategy)) {
      return joinPoint.proceed();
    }
    listeners.forEach(listener -> strategy.pre(listener, args));
    Object result = joinPoint.proceed();
    listeners.forEach(listener -> strategy.post(listener, args));
    return result;
  }

  protected boolean isSkippable(Object entity, List<EntityListenerWrapper> listeners, EntityListenerStrategy strategy) {
    return entity == null || listeners.isEmpty() || strategy == null;
  }
}
