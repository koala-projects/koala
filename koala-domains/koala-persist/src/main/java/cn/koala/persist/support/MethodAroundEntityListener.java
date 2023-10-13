package cn.koala.persist.support;

import cn.koala.persist.AroundEntityListener;
import cn.koala.persist.MethodEntityListenerFactory;
import cn.koala.persist.MethodEntityListenerMethodFactory;
import cn.koala.toolkit.ReflectionHelper;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 方法环绕实体监听器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class MethodAroundEntityListener implements AroundEntityListener {

  private final PlatformTransactionManager transactionManager;
  private final MethodEntityListenerFactory methodEntityListenerFactory;
  private final MethodEntityListenerMethodFactory methodEntityListenerPreMethodFactory;
  private final MethodEntityListenerMethodFactory methodEntityListenerPostMethodFactory;

  @Override
  public Object listen(ProceedingJoinPoint joinPoint) throws Throwable {
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
      List<Object> listeners = methodEntityListenerFactory.getEntityListeners(method, joinPoint.getArgs());
      listeners.forEach(listener -> {
        List<Method> preMethods = methodEntityListenerPreMethodFactory.getEntityListenerMethods(listener, method);
        preMethods.forEach(preMethod -> ReflectionHelper.invokeMethod(preMethod, listener, joinPoint.getArgs()));
      });
      Object result = joinPoint.proceed();
      listeners.forEach(listener -> {
        List<Method> postMethods = methodEntityListenerPostMethodFactory.getEntityListenerMethods(listener, method);
        postMethods.forEach(postMethod -> ReflectionHelper.invokeMethod(postMethod, listener, joinPoint.getArgs()));
      });
      transactionManager.commit(status);
      return result;
    } catch (Throwable throwable) {
      transactionManager.rollback(status);
      throw throwable;
    }
  }
}
