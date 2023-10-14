package cn.koala.persist;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.ObjectUtils;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * TODO: 修改类描述
 *
 * @author Houtaroy
 */
@Aspect
@Order(3200)
@RequiredArgsConstructor
public class CrudServiceEntityListenerAspect {

  private static final Map<String, Class<? extends Annotation>> JPA_PRE_ANNOTATION_MAPPING = Map.of(
    "create", PrePersist.class,
    "update", PreUpdate.class,
    "delete", PreRemove.class
  );

  private static final Map<String, Class<? extends Annotation>> JPA_POST_ANNOTATION_MAPPING = Map.of(
    "create", PostPersist.class,
    "update", PostUpdate.class,
    "delete", PostRemove.class
  );

  private final PlatformTransactionManager transactionManager;
  private final EntityListenerFactory entityListenerFactory;

  @Around("execution(* cn.koala.persist.CrudService+.*(..)) "
    + "&& (execution(* create(..)) || execution(* update(..)) || execution(* delete(..)))")
  public Object aroundEntityCrudMethod(ProceedingJoinPoint joinPoint) throws Throwable {
    DefaultTransactionDefinition def = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
    TransactionStatus status = transactionManager.getTransaction(def);
    try {
      Class<?> entityClass = determineEntityClass(joinPoint);
      Class<? extends Annotation> jpaPreAnnotation = determineJpaPreAnnotation(joinPoint);
      List<EntityListenerMethod> preMethods =
        entityListenerFactory.getEntityListenerMethods(entityClass, jpaPreAnnotation);
      preMethods.forEach(preMethod -> preMethod.invoke(joinPoint.getArgs()));
      Object result = joinPoint.proceed();
      Class<? extends Annotation> jpaPostAnnotation = determineJpaPostAnnotation(joinPoint);
      List<EntityListenerMethod> postMethods =
        entityListenerFactory.getEntityListenerMethods(entityClass, jpaPostAnnotation);
      postMethods.forEach(postMethod -> postMethod.invoke(joinPoint.getArgs()));
      transactionManager.commit(status);
      return result;
    } catch (Throwable throwable) {
      transactionManager.rollback(status);
      throw throwable;
    }
  }

  private Class<?> determineEntityClass(ProceedingJoinPoint joinPoint) {
    return Optional.ofNullable(joinPoint)
      .map(JoinPoint::getArgs)
      .filter(args -> !ObjectUtils.isEmpty(args))
      .map(args -> args[0])
      .map(Object::getClass)
      .orElse(null);
  }

  private Class<? extends Annotation> determineJpaPreAnnotation(ProceedingJoinPoint joinPoint) {
    if (joinPoint.getSignature() instanceof MethodSignature methodSignature) {
      return JPA_PRE_ANNOTATION_MAPPING.get(methodSignature.getName());
    }
    return null;
  }

  private Class<? extends Annotation> determineJpaPostAnnotation(ProceedingJoinPoint joinPoint) {
    if (joinPoint.getSignature() instanceof MethodSignature methodSignature) {
      return JPA_POST_ANNOTATION_MAPPING.get(methodSignature.getName());
    }
    return null;
  }
}
