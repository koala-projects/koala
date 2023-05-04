package cn.koala.persist.support;

import cn.koala.persist.EntityListenerStrategy;
import cn.koala.persist.EntityListenerWrapper;

/**
 * 更新监听器策略
 *
 * @author Houtaroy
 */
public class UpdateEntityListenerStrategy implements EntityListenerStrategy {
  @Override
  public void pre(EntityListenerWrapper listener, Object[] args) {
    listener.preUpdate(args);
  }

  @Override
  public void post(EntityListenerWrapper listener, Object[] args) {
    listener.postUpdate(args);
  }
}
