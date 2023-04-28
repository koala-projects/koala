package cn.koala.persist.listener;

import cn.koala.persist.annotation.Crud;
import cn.koala.toolkit.ClassHelper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 监听器切面
 *
 * @author Houtaroy
 */
@Aspect
@RequiredArgsConstructor
public class ListenerAspect {

  private final List<Listener<?>> listeners;

  @Around("@annotation(crud)")
  public Object around(ProceedingJoinPoint joinPoint, Crud crud) throws Throwable {
    Object[] args = joinPoint.getArgs();
    Assert.isTrue(ArrayUtils.isNotEmpty(args), "被监听方法第一个参数必须为实体对象");
    Object entity = joinPoint.getArgs()[0];
    return joinPoint.proceed();
  }

  protected boolean support(Listener<?> listener, Object entity) {
    return entity.getClass().isAssignableFrom(ClassHelper.getSuperGenericClass(listener, Listener.class));
  }
}
