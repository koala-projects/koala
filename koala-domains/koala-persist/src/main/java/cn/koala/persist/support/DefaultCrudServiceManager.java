package cn.koala.persist.support;

import cn.koala.persist.CrudService;
import cn.koala.persist.CrudServiceManager;
import cn.koala.toolkit.ClassHelper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 默认增删改查服务管理器
 *
 * @author Houtaroy
 */
public class DefaultCrudServiceManager implements CrudServiceManager {

  private final static int GENERIC_CLASSES_SIZE = 2;

  protected final List<CrudServiceWrapper> services;
  protected final Map<AbstractMap.SimpleEntry<Class<?>, Class<?>>, CrudService<?, ?>> cache;

  public DefaultCrudServiceManager(List<CrudService<?, ?>> services) {
    this.services = new ArrayList<>(services.size());
    this.cache = new HashMap<>(services.size());
    services.forEach(this::register);
  }

  protected void register(CrudService<?, ?> service) {
    List<Class<?>> genericClasses = ClassHelper.getGenericClasses(service.getClass(), CrudService.class);
    Assert.notNull(genericClasses, "无法解析服务[%s]的类型".formatted(service.getClass().getName()));
    Assert.isTrue(
      genericClasses.size() == GENERIC_CLASSES_SIZE,
      "无法正确解析服务[%s]的类型".formatted(service.getClass().getName())
    );
    services.add(new CrudServiceWrapper(service, genericClasses.get(0), genericClasses.get(1)));
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T, ID> CrudService<T, ID> getService(Class<T> entityClass, Class<ID> idClass) {
    AbstractMap.SimpleEntry<Class<T>, Class<ID>> key = new AbstractMap.SimpleEntry<>(entityClass, idClass);
    if (cache.containsKey(key)) {
      return (CrudService<T, ID>) cache.get(key);
    }
    return (CrudService<T, ID>) cache.computeIfAbsent(
      new AbstractMap.SimpleEntry<>(entityClass, idClass),
      k -> services.stream()
        .filter(service -> service.support(entityClass, idClass)).findFirst()
        .map(CrudServiceWrapper::getService)
        .orElse(null));
  }

  @Data
  @RequiredArgsConstructor
  static class CrudServiceWrapper {
    private final CrudService<?, ?> service;
    private final Class<?> entityClass;
    private final Class<?> idClass;

    public boolean support(Class<?> entityClass, Class<?> idClass) {
      return this.entityClass.isAssignableFrom(entityClass) && this.idClass.isAssignableFrom(idClass);
    }
  }
}
