package cn.koala.persist.support;

import cn.koala.persist.EntityListenerStrategy;
import cn.koala.persist.EntityListenerWrapper;

/**
 * 新增监听器策略
 *
 * @author Houtaroy
 */
public class CreateEntityListenerStrategy implements EntityListenerStrategy {
  @Override
  public void pre(EntityListenerWrapper listener, Object[] args) {
    listener.prePersist(args);
  }

  @Override
  public void post(EntityListenerWrapper listener, Object[] args) {
    listener.postPersist(args);
  }
}
