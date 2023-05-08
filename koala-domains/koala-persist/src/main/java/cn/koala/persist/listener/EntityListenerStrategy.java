package cn.koala.persist.listener;

/**
 * 监听器策略
 *
 * @author Houtaroy
 */
public interface EntityListenerStrategy {

  default void pre(EntityListenerWrapper listener, Object[] args) {

  }

  default void post(EntityListenerWrapper listener, Object[] args) {

  }
}
