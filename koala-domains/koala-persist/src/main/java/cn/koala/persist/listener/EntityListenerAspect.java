package cn.koala.persist.listener;

import cn.koala.persist.CrudType;
import cn.koala.persist.listener.support.CreateEntityListenerStrategy;
import cn.koala.persist.listener.support.DeleteEntityListenerStrategy;
import cn.koala.persist.listener.support.UpdateEntityListenerStrategy;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.CollectionUtils;
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
  private final PlatformTransactionManager transactionManager;

  private final Map<CrudType, EntityListenerStrategy> strategies = DEFAULT_STRATEGIES;

  public EntityListenerAspect(List<EntityListener> listeners, PlatformTransactionManager transactionManager) {
    this.wrappers = listeners.stream().map(EntityListenerWrapper::from).toList();
    this.transactionManager = transactionManager;
  }

  @Transactional
  @Around("@annotation(action)")
  public Object around(ProceedingJoinPoint joinPoint, EntityListenAction action) throws Throwable {
    Object[] args = joinPoint.getArgs();
    CrudType type = action.value();
    Class<?> entityClass = determineEntityClass(action, args);
    EntityListenerStrategy strategy = strategies.get(type);
    List<EntityListenerWrapper> wrappers = this.getWrappers(entityClass);
    DefaultTransactionDefinition def = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
    TransactionStatus status = transactionManager.getTransaction(def);
    try {
      boolean listenable = isListenable(entityClass, wrappers, strategy);
      if (listenable && action.pre()) {
        wrappers.forEach(wrapper -> strategy.pre(wrapper, args));
      }
      Object result = joinPoint.proceed();
      if (listenable && action.post()) {
        wrappers.forEach(wrapper -> strategy.post(wrapper, args));
      }
      transactionManager.commit(status);
      return result;
    } catch (Throwable throwable) {
      transactionManager.rollback(status);
      throw throwable;
    }
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

  private boolean isListenable(Class<?> entityClass, List<EntityListenerWrapper> wrappers,
                               EntityListenerStrategy strategy) {
    return entityClass != null && !CollectionUtils.isEmpty(wrappers) && strategy != null;
  }
}
