package cn.koala.persist.service;

import cn.koala.persist.CrudService;

import java.util.Optional;

/**
 * 增删改查服务注册表
 *
 * @author Houtaroy
 */
@Deprecated
public interface CrudServiceRegistry {

  Optional<CrudService<?, ?>> getService(Class<?> entityClass);
}
