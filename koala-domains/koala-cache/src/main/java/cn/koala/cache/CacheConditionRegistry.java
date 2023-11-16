package cn.koala.cache;

import java.util.Optional;
import java.util.Set;

/**
 * 缓存条件注册表
 *
 * @author Houtaroy
 */
public interface CacheConditionRegistry {

  Optional<CacheCondition> get(Set<String> cacheNames);
}
