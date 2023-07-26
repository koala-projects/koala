package cn.koala.persist.listener.support;

import cn.koala.persist.listener.EntityListenerStrategy;
import cn.koala.persist.listener.EntityListenerWrapper;

/**
 * 更新监听器策略
 *
 * @author Houtaroy
 */
public class UpdateEntityListenerStrategy implements EntityListenerStrategy {
  @Override
  public void pre(EntityListenerWrapper wrapper, Object[] args) {
    wrapper.preUpdate(args);
  }

  @Override
  public void post(EntityListenerWrapper wrapper, Object[] args) {
    wrapper.postUpdate(args);
  }
}
