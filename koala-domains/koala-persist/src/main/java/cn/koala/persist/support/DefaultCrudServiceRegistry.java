package cn.koala.persist.support;

import cn.koala.persist.CrudService;
import cn.koala.persist.CrudServiceRegistry;
import cn.koala.toolkit.ClassHelper;
import cn.koala.toolkit.registry.AbstractCacheableRegistry;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 默认增删改查服务注册表
 * <p>
 * 在注册时获取服务对应的实体类型, 并依此进行键值关系判断
 *
 * @author Houtaroy
 */
public class DefaultCrudServiceRegistry extends AbstractCacheableRegistry<Class<?>, CrudService<?, ?>>
  implements CrudServiceRegistry {

  protected final Map<CrudService<?, ?>, Class<?>> entityClasses;

  public DefaultCrudServiceRegistry(List<CrudService<?, ?>> services) {
    super();
    this.entityClasses = new ConcurrentReferenceHashMap<>(services.size());
    services.forEach(this::register);
  }

  @Override
  public void register(CrudService<?, ?> value) {
    super.register(value);
    this.entityClasses.put(value, ClassHelper.getGenericClass(value.getClass(), CrudService.class));
  }

  @Override
  public void unregister(CrudService<?, ?> value) {
    super.unregister(value);
    this.entityClasses.remove(value);
  }

  @Override
  protected boolean matches(Class<?> key, CrudService<?, ?> value) {
    return Optional.ofNullable(entityClasses.get(value))
      .map(entityClass -> entityClass.isAssignableFrom(key))
      .orElse(false);
  }
}
