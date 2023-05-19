package cn.koala.persist.listener;

import cn.koala.toolkit.registry.Registry;

/**
 * 实体监听器注册表
 *
 * @author Houtaroy
 */
public interface EntityListenerRegistry extends Registry<Class<?>, EntityListenerWrapper> {
}
