package cn.koala.cache.interceptor.support;

import cn.koala.cache.interceptor.CacheCondition;
import cn.koala.cache.interceptor.CacheConditionRegistrar;
import cn.koala.cache.interceptor.CacheConditionRegistry;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 默认缓存注册表
 *
 * @author Houtaroy
 */
public class DefaultCacheConditionRegistry implements CacheConditionRegistry {

  private final List<CacheConditionRegistrar> registrars;
  private final Map<Set<String>, CacheCondition> cache = new HashMap<>();

  public DefaultCacheConditionRegistry() {
    this.registrars = new ArrayList<>();
  }

  public DefaultCacheConditionRegistry(List<CacheConditionRegistrar> registrars) {
    this.registrars = registrars;
  }

  @Override
  public void register(CacheConditionRegistrar registrar) {
    registrars.add(registrar);
  }

  @Override
  @Nullable
  public CacheCondition getCacheCondition(Set<String> cacheNames) {
    if (cache.containsKey(cacheNames)) {
      return cache.get(cacheNames);
    }
    CacheCondition result = registrars.stream()
      .filter(registrar -> registrar.getCacheNames().containsAll(cacheNames))
      .findFirst()
      .map(CacheConditionRegistrar::getCacheCondition)
      .orElse(null);
    cache.put(cacheNames, result);
    return result;
  }
}
