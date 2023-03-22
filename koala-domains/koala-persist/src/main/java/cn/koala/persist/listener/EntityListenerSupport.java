package cn.koala.persist.listener;

import java.util.Optional;

/**
 * 监听器支持接口
 *
 * @author Houtaroy
 */
public interface EntityListenerSupport {
  void registerListener(EntityListener listener);

  Optional<Class<?>> getEntityType();
}
