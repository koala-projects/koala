package cn.koala.persist;

import org.springframework.lang.Nullable;

/**
 * 增删改查服务管理器
 *
 * @author Houtaroy
 */
public interface CrudServiceManager {

  @Nullable
  <T> CrudService<T, Object> getService(Class<T> entityClass);
}
