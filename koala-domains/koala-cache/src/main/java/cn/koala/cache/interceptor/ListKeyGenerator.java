package cn.koala.cache.interceptor;

import cn.koala.toolkit.ArrayHelper;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 列表或分页查询缓存键值生成器
 *
 * @author Houtaroy
 */
public class ListKeyGenerator implements KeyGenerator {

  private static final String LIST_KEY_TEMPLATE = "{parameters:%s,pageable:%s}";

  @Override
  @NonNull
  @SuppressWarnings("unchecked")
  public Object generate(@NonNull Object target, @NonNull Method method, @NonNull Object... params) {
    return LIST_KEY_TEMPLATE.formatted(
      generateParameters(ArrayHelper.get(params, Map.class)),
      generatePageable(ArrayHelper.get(params, Pageable.class))
    );
  }

  private String generateParameters(Map<String, Object> parameters) {
    StringBuilder sb = new StringBuilder("{");
    if (!CollectionUtils.isEmpty(parameters)) {
      sb.append(parameters.entrySet().stream()
        .sorted(Map.Entry.comparingByKey())
        .map(entry -> entry.getKey() + "=" + entry.getValue())
        .collect(Collectors.joining(", ")));
    }
    sb.append("}");
    return sb.toString();
  }

  private String generatePageable(Pageable pageable) {
    StringBuilder sb = new StringBuilder("{");
    if (pageable != null) {
      sb.append("page=%d,".formatted(pageable.getPageNumber()));
      sb.append("size=%d,".formatted(pageable.getPageSize()));
      sb.append("sort='%s'".formatted(pageable.getSort()));
    }
    sb.append("}");
    return sb.toString();
  }
}
