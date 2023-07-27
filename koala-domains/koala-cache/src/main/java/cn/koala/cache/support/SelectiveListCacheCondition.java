package cn.koala.cache.support;

import cn.koala.cache.CacheCondition;
import cn.koala.toolkit.ArrayHelper;
import org.springframework.cache.Cache;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 可选择的列表或分页查询缓存条件
 * <p>
 * 初始化时传入字段名, 只有当查询条件中的字段完全匹配时, 才会判断需要缓存
 *
 * @author Houtaroy
 */
public class SelectiveListCacheCondition implements CacheCondition {

  private final Set<String> parameterNames;

  public SelectiveListCacheCondition(String... parameterName) {
    this.parameterNames = new HashSet<>(Arrays.asList(parameterName));
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean matches(Object target, Method method, Collection<Cache> caches, Object... params) {
    Map<String, Object> parameters = ArrayHelper.get(params, Map.class);
    return !CollectionUtils.isEmpty(parameters) && parameters.keySet().containsAll(parameterNames);
  }
}
