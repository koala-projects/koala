package cn.koala.cache;

import org.springframework.cache.Cache;

import java.lang.reflect.Method;
import java.util.Collection;

/**
 * 缓存条件
 *
 * @author Houtaroy
 */
public interface CacheCondition {

  boolean matches(Object target, Method method, Collection<Cache> caches, Object... params);
}
