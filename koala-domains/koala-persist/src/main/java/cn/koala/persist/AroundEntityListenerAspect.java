package cn.koala.persist;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * 环绕实体监听器切面
 *
 * @author Houtaroy
 */
@Aspect
@RequiredArgsConstructor
public class AroundEntityListenerAspect {

  private final AroundEntityListener listener;

  @Around("execution(* cn.koala.persist.CrudService+.*(..)) "
    + "&& (execution(* create(..)) || execution(* update(..)) || execution(* delete(..)))")
  public Object aroundEntityCrudMethod(ProceedingJoinPoint joinPoint) throws Throwable {
    return listener.listen(joinPoint);
  }
}
