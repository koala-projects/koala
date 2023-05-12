package cn.koala.cache.interceptor.support;

import cn.koala.cache.interceptor.CacheCondition;
import cn.koala.cache.interceptor.CacheConditionRegistrar;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

/**
 * 简易缓存条件注册器
 * <p>
 * 模块内部注册缓存条件时使用
 *
 * @author Houtaroy
 */
@Getter
@SuperBuilder(toBuilder = true)
public class SimpleCacheConditionRegistrar implements CacheConditionRegistrar {

  private final Set<String> cacheNames;
  private final CacheCondition cacheCondition;
}
