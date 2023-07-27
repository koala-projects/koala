package cn.koala.cache;

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
