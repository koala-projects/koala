package cn.koala.cache;

import cn.koala.toolkit.registry.Registry;

import java.util.Set;

/**
 * 缓存条件注册表
 *
 * @author Houtaroy
 */
public interface CacheConditionRegistry extends Registry<Set<String>, CacheConditionRegistration> {
}
