package cn.koala.persist.support;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * 增删改查方法实体类型解析器
 * <p>
 * 获取第一个参数的类型为实体类型
 *
 * @author Houtaroy
 */
public class CrudMethodEntityClassResolver implements EntityClassResolver {

  @Override
  public Class<?> resolveEntityClass(Method method, Object[] args) {
    return Optional.ofNullable(args)
      .filter(objects -> objects.length > 0)
      .map(objects -> objects[0].getClass())
      .orElse(null);
  }
}
