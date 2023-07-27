package cn.koala.cache.annotation;

import cn.koala.cache.support.CacheNames;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 查看缓存注解
 * <p>
 * 设置了默认键值生成器
 *
 * @author Houtaroy
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Cacheable
public @interface LoadCacheable {

  @AliasFor(annotation = Cacheable.class)
  String[] value();

  @AliasFor(annotation = Cacheable.class, attribute = "keyGenerator")
  String keyGenerator() default CacheNames.DEFAULT_LOAD_KEY_GENERATOR;
}
