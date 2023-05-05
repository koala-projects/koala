package cn.koala.persist;

/**
 * 增删改查服务管理器
 *
 * @author Houtaroy
 */
public interface CrudServiceManager {
  <T, ID> CrudService<T, ID> getService(Class<T> entityClass, Class<ID> idClass);
}
