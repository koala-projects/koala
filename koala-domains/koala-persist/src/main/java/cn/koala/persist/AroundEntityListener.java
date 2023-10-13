package cn.koala.persist;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 环绕切面实体监听器
 *
 * @author Houtaroy
 */
public interface AroundEntityListener {

  Object listen(ProceedingJoinPoint joinPoint) throws Throwable;
}
