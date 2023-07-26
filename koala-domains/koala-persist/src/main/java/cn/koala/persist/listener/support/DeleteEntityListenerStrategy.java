package cn.koala.persist.listener.support;

import cn.koala.persist.listener.EntityListenerStrategy;
import cn.koala.persist.listener.EntityListenerWrapper;

/**
 * 删除监听器策略
 *
 * @author Houtaroy
 */
public class DeleteEntityListenerStrategy implements EntityListenerStrategy {
  @Override
  public void pre(EntityListenerWrapper wrapper, Object[] args) {
    wrapper.preDelete(args);
  }

  @Override
  public void post(EntityListenerWrapper wrapper, Object[] args) {
    wrapper.postDelete(args);
  }
}
