package cn.koala.log;

import java.lang.reflect.Method;

/**
 * 日志表达式RootObject
 *
 * @author Houtaroy
 */
public record LogExpressionRootObject(Method method, Object[] args, Object target, Class<?> targetClass) {
}
