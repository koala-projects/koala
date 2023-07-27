package cn.koala.cache.interceptor;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.lang.reflect.Method;

/**
 * 查看方法缓存键值生成器
 *
 * @author Houtaroy
 */
public class LoadKeyGenerator implements KeyGenerator {
  @Override
  @NonNull
  public Object generate(@NonNull Object target, @NonNull Method method, @NonNull Object... params) {
    Assert.isTrue(params.length > 0, "查看方法至少应有一个入参");
    return params[0];
  }
}
