package cn.koala.cache.support;

import cn.koala.cache.CacheCondition;
import cn.koala.cache.CacheConditionRegistration;
import cn.koala.cache.CacheConditionRegistry;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 默认缓存条件注册表
 *
 * @author Houtaroy
 */
public class DefaultCacheConditionRegistry implements CacheConditionRegistry {

  private final List<CacheConditionRegistration> registrations;

  public DefaultCacheConditionRegistry(List<CacheConditionRegistration> registrations) {
    this.registrations = registrations;
  }

  @Override
  public Optional<CacheCondition> get(Set<String> cacheNames) {
    return registrations.stream()
      .filter(registration -> registration.getCacheNames().containsAll(cacheNames))
      .findFirst()
      .map(CacheConditionRegistration::getCacheCondition);
  }
}
