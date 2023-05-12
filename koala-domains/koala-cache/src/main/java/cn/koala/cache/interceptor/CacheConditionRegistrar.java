package cn.koala.cache.interceptor;

import java.util.Set;

/**
 * 缓存条件注册器
 *
 * @author Houtaroy
 */
public interface CacheConditionRegistrar {
  Set<String> getCacheNames();

  CacheCondition getCacheCondition();
}
