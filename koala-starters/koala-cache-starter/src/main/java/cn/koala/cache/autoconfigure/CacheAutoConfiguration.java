package cn.koala.cache.autoconfigure;

import cn.koala.cache.CacheCondition;
import cn.koala.cache.CacheConditionRegistration;
import cn.koala.cache.CacheConditionRegistry;
import cn.koala.cache.interceptor.ListKeyGenerator;
import cn.koala.cache.interceptor.LoadKeyGenerator;
import cn.koala.cache.support.CompositeCacheCondition;
import cn.koala.cache.support.DefaultCacheConditionRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.util.List;

/**
 * 缓存自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CacheAutoConfiguration {

  private final RedisConnectionFactory redisConnectionFactory;

  @Bean
  @ConditionalOnMissingBean
  public CacheManager cacheManager() {
    return RedisCacheManager.builder(redisConnectionFactory).build();
  }

  @Bean
  @ConditionalOnMissingBean(name = "listKeyGenerator")
  public ListKeyGenerator listKeyGenerator() {
    return new ListKeyGenerator();
  }

  @Bean
  @ConditionalOnMissingBean
  public LoadKeyGenerator loadKeyGenerator() {
    return new LoadKeyGenerator();
  }

  @Bean
  @ConditionalOnMissingBean
  public CacheConditionRegistry cacheConditionRegistry(List<CacheConditionRegistration> registrars) {
    return new DefaultCacheConditionRegistry(registrars);
  }

  @Bean
  @ConditionalOnMissingBean
  public CacheCondition cacheCondition(CacheConditionRegistry registry) {
    return new CompositeCacheCondition(registry);
  }
}
