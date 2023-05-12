package cn.koala.cache;

import cn.koala.cache.interceptor.support.CacheNames;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 列表或分页查询缓存注解
 * <p>
 * 设置了默认键值生成器和默认缓存条件
 *
 * @author Houtaroy
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Cacheable
public @interface ListCacheable {

  @AliasFor(annotation = Cacheable.class)
  String[] value();

  @AliasFor(annotation = Cacheable.class, attribute = "keyGenerator")
  String keyGenerator() default CacheNames.DEFAULT_LIST_KEY_GENERATOR;

  @AliasFor(annotation = Cacheable.class, attribute = "condition")
  String condition() default CacheNames.DEFAULT_CONDITION;
}
