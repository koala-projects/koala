package cn.koala.cache.interceptor.support;

/**
 * 缓存模块名称常量
 *
 * @author Houtaroy
 */
public abstract class CacheNames {
  public static final String DEFAULT_LOAD_KEY_GENERATOR = "loadKeyGenerator";
  public static final String DEFAULT_LIST_KEY_GENERATOR = "listKeyGenerator";
  public static final String DEFAULT_CONDITION = "@cacheCondition.isCacheable(#root.target, #root.method, #root.caches, #root.args)";
}
