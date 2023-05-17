package cn.koala.persist.support;

import cn.koala.persist.CrudService;
import cn.koala.persist.CrudServiceManager;
import cn.koala.toolkit.ClassHelper;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 默认增删改查服务管理器
 *
 * @author Houtaroy
 */
public class DefaultCrudServiceManager implements CrudServiceManager {

  protected final List<CrudServiceWrapper> wrappers;
  protected final Map<Class<?>, CrudService<?, ?>> cache;

  public DefaultCrudServiceManager(List<CrudService<?, ?>> services) {
    this.wrappers = services.stream().map(CrudServiceWrapper::wrap).toList();
    this.cache = new HashMap<>(services.size());
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> CrudService<T, Object> getService(Class<T> entityClass) {
    if (cache.containsKey(entityClass)) {
      return (CrudService<T, Object>) cache.get(entityClass);
    }
    return (CrudService<T, Object>) cache.computeIfAbsent(
      entityClass,
      k -> wrappers.stream()
        .filter(service -> service.support(entityClass))
        .findFirst()
        .map(CrudServiceWrapper::service)
        .orElse(null));
  }

  record CrudServiceWrapper(CrudService<?, ?> service, Class<?> entityClass) {
    public boolean support(Class<?> entityClass) {
      return this.entityClass.isAssignableFrom(entityClass);
    }

    public static CrudServiceWrapper wrap(CrudService<?, ?> service) {
      Class<?> entityClass = ClassHelper.getGenericClass(service.getClass(), CrudService.class);
      Assert.notNull(entityClass, "无法解析服务[%s]的实体类型".formatted(service.getClass().getName()));
      return new CrudServiceWrapper(service, entityClass);
    }
  }
}
