package cn.koala.persist.listener;

/**
 * 监听器策略
 *
 * @author Houtaroy
 */
public interface EntityListenerStrategy {

  default void pre(EntityListenerWrapper wrapper, Object[] args) {

  }

  default void post(EntityListenerWrapper wrapper, Object[] args) {

  }
}
