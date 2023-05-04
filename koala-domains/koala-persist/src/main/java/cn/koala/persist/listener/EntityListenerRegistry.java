package cn.koala.persist.listener;

/**
 * 实体监听器注册器
 *
 * @author Houtaroy
 */
@Deprecated
public interface EntityListenerRegistry {
  void register(EntityListenerSupport<?> support);
}
