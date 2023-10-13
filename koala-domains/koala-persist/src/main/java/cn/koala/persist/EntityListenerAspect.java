package cn.koala.persist;

import cn.koala.toolkit.ReflectionHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 实体监听器切面
 *
 * @author Houtaroy
 */
@Aspect
@Order(3200)
public class EntityListenerAspect {

  private final EntityListenerFactory factory;
  private final EntityListenerMethodFactory methodFactory;
  private final EntityListenerMethodAnnotationFactory annotationFactory;

  private final PlatformTransactionManager transactionManager;

  public EntityListenerAspect(EntityListenerFactory factory, EntityListenerMethodFactory methodFactory,
                              EntityListenerMethodAnnotationFactory annotationFactory,
                              PlatformTransactionManager transactionManager) {

    this.factory = factory;
    this.methodFactory = methodFactory;
    this.annotationFactory = annotationFactory;
    this.transactionManager = transactionManager;
  }

  @Around("execution(* cn.koala.persist.CrudService+.*(..)) "
    + "&& (execution(* create(..)) || execution(* update(..)) || execution(* delete(..)))")
  public Object aroundEntityCrudMethod(ProceedingJoinPoint joinPoint) throws Throwable {
    Signature signature = joinPoint.getSignature();
    if (signature instanceof MethodSignature methodSignature) {
      return doListen(joinPoint, methodSignature.getMethod());
    }
    return joinPoint.proceed();
  }

  public Object doListen(ProceedingJoinPoint joinPoint, Method method) throws Throwable {
    DefaultTransactionDefinition def = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
    TransactionStatus status = transactionManager.getTransaction(def);
    try {
      Class<? extends Annotation> preAnnotation = annotationFactory.getEntityListenerMethodPreAnnotation(method);
      Class<? extends Annotation> postAnnotation = annotationFactory.getEntityListenerMethodPostAnnotation(method);
      List<Object> listeners = factory.getEntityListeners(joinPoint.getArgs()[0].getClass());
      listeners.forEach(listener -> {
        List<Method> pres = methodFactory.getEntityListenerMethods(listener, preAnnotation);
        pres.forEach(pre -> ReflectionHelper.invokeMethod(pre, listener, joinPoint.getArgs()));
      });
      Object result = joinPoint.proceed();
      listeners.forEach(listener -> {
        List<Method> posts = methodFactory.getEntityListenerMethods(listener, postAnnotation);
        posts.forEach(post -> ReflectionHelper.invokeMethod(post, listener, joinPoint.getArgs()));
      });
      transactionManager.commit(status);
      return result;
    } catch (Throwable throwable) {
      transactionManager.rollback(status);
      throw throwable;
    }
  }
}
