package cn.koala.cache.interceptor;

import java.util.Set;

/**
 * 缓存条件注册证
 *
 * @author Houtaroy
 */
public interface CacheConditionRegistration {

  Set<String> getCacheNames();

  CacheCondition getCacheCondition();
}
