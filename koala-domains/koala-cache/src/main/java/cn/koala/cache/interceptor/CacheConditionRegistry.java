package cn.koala.cache.interceptor;

import java.util.Set;

/**
 * 缓存条件注册表
 *
 * @author Houtaroy
 */
public interface CacheConditionRegistry {
  void register(CacheConditionRegistrar registrar);

  CacheCondition getCacheCondition(Set<String> cacheNames);
}
