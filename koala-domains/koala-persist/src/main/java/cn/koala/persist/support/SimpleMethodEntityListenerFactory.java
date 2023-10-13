package cn.koala.persist.support;

import cn.koala.persist.EntityListenerFactory;
import cn.koala.persist.MethodEntityListenerFactory;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 简易方法实体监听器工厂
 * <p>
 * 内部整合了{@link EntityClassResolver}和{@link EntityListenerFactory}
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class SimpleMethodEntityListenerFactory implements MethodEntityListenerFactory {

  private final EntityClassResolver entityClassResolver;
  private final EntityListenerFactory entityListenerFactory;

  @Override
  public List<Object> getEntityListeners(Method method, Object[] args) {
    return entityListenerFactory.getEntityListeners(entityClassResolver.resolveEntityClass(method, args));
  }
}
