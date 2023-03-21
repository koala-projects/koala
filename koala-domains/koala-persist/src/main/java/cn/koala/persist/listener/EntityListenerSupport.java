package cn.koala.persist.listener;

/**
 * 监听器支持接口
 *
 * @author Houtaroy
 */
public interface EntityListenerSupport {
  void registerListener(EntityListener listener);
}
