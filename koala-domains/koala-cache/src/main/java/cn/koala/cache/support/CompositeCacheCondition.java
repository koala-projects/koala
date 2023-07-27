package cn.koala.cache.support;

import cn.koala.cache.CacheCondition;
import cn.koala.cache.CacheConditionRegistration;
import cn.koala.cache.CacheConditionRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 聚合缓存条件
 * <p>
 * 内部整合了缓存条件注册表{@link CacheConditionRegistry}, 根据缓存名称查找对应的缓存条件
 * <p>
 * 如果缓存条件不存在或判断成功, 则认为需要进行缓存
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class CompositeCacheCondition implements CacheCondition {

  private final CacheConditionRegistry registry;

  @Override
  public boolean matches(Object target, Method method, Collection<Cache> caches, Object... params) {
    Set<String> cacheNames = caches.stream().map(Cache::getName).collect(Collectors.toSet());
    return registry.get(cacheNames)
      .map(CacheConditionRegistration::getCacheCondition)
      .map(condition -> condition.matches(target, method, caches, params))
      .orElse(true);
  }
}
