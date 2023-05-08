package cn.koala.persist;

import org.springframework.lang.Nullable;

/**
 * 增删改查服务管理器
 *
 * @author Houtaroy
 */
public interface CrudServiceManager {

  @Nullable
  <T, ID> CrudService<T, ID> getService(Class<T> entityClass, Class<ID> idClass);
}
