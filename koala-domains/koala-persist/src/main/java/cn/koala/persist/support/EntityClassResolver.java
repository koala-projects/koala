package cn.koala.persist.support;

import java.lang.reflect.Method;

/**
 * 实体类型解析器
 *
 * @author Houtaroy
 */
public interface EntityClassResolver {

  Class<?> resolveEntityClass(Method method, Object[] args);
}
