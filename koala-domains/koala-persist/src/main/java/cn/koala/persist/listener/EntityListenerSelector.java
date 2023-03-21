package cn.koala.persist.listener;

/**
 * 实体监听器选择器
 *
 * @author Houtaroy
 */
public interface EntityListenerSelector {
  boolean match(Class<?> entityType);
}
