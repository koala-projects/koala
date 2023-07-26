package cn.koala.persist.listener.support;

import cn.koala.persist.listener.EntityListenerStrategy;
import cn.koala.persist.listener.EntityListenerWrapper;

/**
 * 新增监听器策略
 *
 * @author Houtaroy
 */
public class CreateEntityListenerStrategy implements EntityListenerStrategy {
  @Override
  public void pre(EntityListenerWrapper wrapper, Object[] args) {
    wrapper.prePersist(args);
  }

  @Override
  public void post(EntityListenerWrapper wrapper, Object[] args) {
    wrapper.postPersist(args);
  }
}
