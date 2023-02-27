package cn.koala.log;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Method;

/**
 * 日志表达式RootObject
 *
 * @author Houtaroy
 */
@Getter
@AllArgsConstructor
public class LogExpressionRootObject {
  private final Method method;

  private final Object[] args;

  private final Object target;

  private final Class<?> targetClass;
}
