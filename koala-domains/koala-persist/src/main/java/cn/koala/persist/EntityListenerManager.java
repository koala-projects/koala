package cn.koala.persist;

import lombok.NonNull;

import java.util.List;

/**
 * 实体监听器管理器
 *
 * @author Houtaroy
 */
public interface EntityListenerManager {
  List<EntityListenerWrapper> getListeners(@NonNull Object entity);
}
