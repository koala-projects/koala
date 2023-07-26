package cn.koala.persist.service;

import java.util.Optional;

/**
 * 增删改查服务注册表
 *
 * @author Houtaroy
 */
public interface CrudServiceRegistry {

  Optional<CrudService<?, ?>> getService(Class<?> entityClass);
}
