package cn.koala.persist.service.support;

import cn.koala.persist.service.CrudService;
import cn.koala.persist.service.CrudServiceRegistry;
import cn.koala.toolkit.ClassHelper;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 基于内存的增删改查服务注册表
 *
 * @author Houtaroy
 */
public class InMemoryCrudServiceRegistry implements CrudServiceRegistry {

  private final Map<Class<?>, CrudService<?, ?>> services;

  public InMemoryCrudServiceRegistry(List<CrudService<?, ?>> services) {
    this.services = services.stream().collect(Collectors.toMap(
      service -> ClassHelper.getGenericClass(service.getClass(), CrudService.class),
      service -> service
    ));
  }

  @Override
  public Optional<CrudService<?, ?>> getService(Class<?> entityClass) {
    return this.services.keySet().stream()
      .filter(key -> key.isAssignableFrom(entityClass))
      .findFirst()
      .map(this.services::get);
  }
}
