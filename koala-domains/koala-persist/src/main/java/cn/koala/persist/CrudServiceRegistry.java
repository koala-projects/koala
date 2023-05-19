package cn.koala.persist;

import cn.koala.toolkit.registry.Registry;

/**
 * 增删改查服务注册表
 *
 * @author Houtaroy
 */
public interface CrudServiceRegistry extends Registry<Class<?>, CrudService<?, ?>> {
}
