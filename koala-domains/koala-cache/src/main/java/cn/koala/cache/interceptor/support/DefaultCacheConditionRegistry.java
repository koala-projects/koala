package cn.koala.cache.interceptor.support;

import cn.koala.cache.interceptor.CacheConditionRegistration;
import cn.koala.cache.interceptor.CacheConditionRegistry;
import cn.koala.toolkit.registry.AbstractCacheableRegistry;

import java.util.List;
import java.util.Set;

/**
 * 默认缓存条件注册表
 *
 * @author Houtaroy
 */
public class DefaultCacheConditionRegistry extends AbstractCacheableRegistry<Set<String>, CacheConditionRegistration>
  implements CacheConditionRegistry {

  public DefaultCacheConditionRegistry(List<CacheConditionRegistration> registrations) {
    super(registrations);
  }

  @Override
  protected boolean matches(Set<String> key, CacheConditionRegistration registration) {
    return registration.getCacheNames().containsAll(key);
  }
}
